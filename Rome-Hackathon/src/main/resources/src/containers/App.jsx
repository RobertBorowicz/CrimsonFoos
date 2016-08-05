import React from 'react';
import './app.scss';
import {Circle} from 'better-react-spinkit';
//import MockData from '../mocks/MockData.jsx';
//import WebApiClient from '../ajax/WebApiClient.jsx';
import NavMenu from '../components/nav_menu/NavMenu.jsx';
import NewGame from '../components/new_game/NewGame.jsx';
import ScoresView from '../components/scores_view/ScoresView.jsx';
import PlayersView from '../components/players_view/PlayersView.jsx';
import MatchupsView from '../components/matchups_view/MatchupsView.jsx';
import StatsView from '../components/stats_view/StatsView.jsx';

export default class App extends React.Component {

    static newGameView = 'newGameView';
    static scoresView = 'scoresView';
    static statsView = 'statsView';
    static playersView = 'playersView';
    static matchupsView = 'matchupsView';
    static loading = 'loading';
    static error = 'error';

    state = {view: null, viewName: null, players: null, games: null};

    // test state -- remove for production
    /*constructor(props) {
        super(props);
        let players = MockData.getPlayers();
        let games = MockData.getGames();
        let teams = MockData.getTeams();
        this.state = {
            view: (
                <NewGame
                    players={players}
                    onSubmit={this.handleGetMatchups.bind(this)}
                />
            ),
            viewName: App.newGameView,
            players: players,
            games: games,
            teams: teams
        };
    }*/

    componentDidMount() {
        this.fetchBulkData();  // production call
    }

    fetchBulkData() {
        let request = new XMLHttpRequest();
        request.open('GET', '/api/bulk/', true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let bulk = JSON.parse(request.responseText);
                console.log(bulk);
                if (bulk) {
                    this.setState({
                        view: (
                            <NewGame
                                players={bulk.players}
                                onSubmit={this.handleGetMatchups.bind(this)}
                            />
                        ),
                        viewName: App.newGameView,
                        players: bulk.players,
                        games: bulk.games,
                        teams: bulk.teams
                    });
                }
            }
        };
        request.send();
    }

    /* Handle requests to toggle view */

    handleNewGameView() {
        if (this.state.viewName !== App.newGameView) {
            this.setState({
                view: (
                    <NewGame
                        players={JSON.parse(JSON.stringify(this.state.players))}
                        onSubmit={this.handleGetMatchups.bind(this)}
                    />
                ),
                viewName: App.newGameView
            });
        }
    }

    handleScoresView() {
        console.log('handling scores view');
        if (this.state.viewName !== App.scoresView) {
            this.setState({
                view: <ScoresView games={this.state.games} />,
                viewName: App.scoresView
            });
        }
    }

    handleStatsView() {
        if (this.state.viewName !== App.statsView) {
            this.setState({
                view: <StatsView players={this.state.players} teams={this.state.teams} />,
                viewName: App.statsView
            });
        }
    }

    handlePlayersView(players) {
        if (this.state.viewName !== App.playersView) {
            let p = players ? players : this.state.players;
            this.setState({
                view: (
                    <PlayersView
                        players={p}
                        onCreate={this.handleCreatePlayer.bind(this)}
                        onUpdate={this.handleUpdatePlayer.bind(this)}
                        onDelete={this.handleDeletePlayer.bind(this)}
                    />
                ),
                viewName: App.playersView,
                players: p
            });
        }
    }

    handlePlayGame() {
        console.log('playing game');
    }

    /* Handle server API requests */

    handleGetMatchups(players) {
        this.setState({view: <Circle className='loading' size={50} />, viewName: App.loading});
        let request = new XMLHttpRequest();
        request.open('POST', '/api/matchups/', true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                this.setState({
                    view: (
                        <MatchupsView
                            matchups={JSON.parse(request.responseText)}
                            onSubmit={this.handlePlayGame.bind(this)}
                        />
                    ),
                    viewName: App.matchupsView
                });
            } else {
                this.setState({
                    view: <Error onConfirmError={this.handleNewGameView.bind(this)}/>,
                    viewName: App.error
                });
            }
        };
        request.send(players);
    }

    handleCreatePlayer(player) {
        this.setState({view: <Circle className='loading' size={50} />, viewName: App.loading});
        let request = new XMLHttpRequest();
        request.open('POST', '/api/player/', true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let players = JSON.parse(request.responseText);
                this.setState({
                    view: this.handlePlayersView(players),
                    viewName: App.playersView,
                    players: players
                });
            } else {
                this.setState({
                    view: <Error onConfirmError={this.handlePlayersView.bind(this)}/>,
                    viewName: App.error
                });
            }
        };
        request.send(player);
    }

    handleUpdatePlayer(player) {
        this.setState({view: <div className='loading'><Circle size={200} /></div>, viewName: App.loading});
        let request = new XMLHttpRequest();
        request.open('PUT', '/api/player/' + player.id, true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let players = this.state.players;
                let index = this.findPlayer(player.id);
                if (index !== -1) {
                    players[index] = player;
                    this.setState({
                        view: this.handlePlayersView(players),
                        viewName: App.playersView,
                        players: players
                    });
                }
            } else {
                this.setState({
                    view: <Error onConfirmError={this.handlePlayersView.bind(this)}/>,
                    viewName: App.error
                });
            }
        };
        request.send(player);
    }

    handleDeletePlayer(player) {
        this.setState({view: <Circle className='loading' size={50} />, viewName: App.loading});
        let request = new XMLHttpRequest();
        request.open('DELETE', '/api/player/' + player.id, true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let players = this.state.players;
                let index = this.findPlayer(player.id);
                if (index !== -1) {
                    players.splice(index, 1);
                    this.setState({
                        view: this.handlePlayersView(players),
                        viewName: App.playersView,
                        players: players
                    });
                }
            } else {
                this.setState({
                    view: <Error onConfirmError={this.handlePlayersView.bind(this)}/>,
                    viewName: App.error
                });
            }
        };
        request.send();
    }

    findPlayer(id) {
        for (let i = 0; i < this.state.players.length; i++) {
            if (this.state.players[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    render() {
        return (
            <div className='app'>
                <div id='app-menu'>
                    <NavMenu
                        onNewGameView={this.handleNewGameView.bind(this)}
                        onScoresView={this.handleScoresView.bind(this)}
                        onStatsView={this.handleStatsView.bind(this)}
                        onPlayersView={this.handlePlayersView.bind(this)}
                    />
                </div>
                {this.state.view ? <Circle size={50} /> : this.state.view}
            </div>
        );
    }
}