import React from 'react';
import './app.scss';
import {Circle} from 'better-react-spinkit';
import MockData from '../mocks/MockData.jsx';
import WebApiClient from '../ajax/WebApiClient.jsx';
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

    state = {view: null, viewName: null, players: null, games: null};

    // test state -- remove for production
    constructor(props) {
        super(props);
        this.players = MockData.getPlayers();
        let games = MockData.getGames();
        this.state = {
            view: (
                <NewGame
                    players={this.players}
                    onSubmit={this.handleGetMatchups.bind(this)}
                />
            ),
            viewName: App.newGameView,
            players: this.players,
            games: games
        };
    }

    componentDidMount() {
        //this.fetchBulkData();  // production call
    }

    fetchBulkData() {
        let request = new XMLHttpRequest();
        request.open('GET', '/api/bulk/', true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let bulk = JSON.parse(request.responseText);
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
                        games: bulk.games
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
                view: <StatsView />,
                viewName: App.statsView
            });
        }
    }

    handlePlayersView() {
        if (this.state.viewName !== App.playersView) {
            this.setState({
                view: (
                    <PlayersView
                        players={this.state.players}
                        onCreate={this.handleCreatePlayer.bind(this)}
                        onUpdate={this.handleUpdatePlayer.bind(this)}
                        onDelete={this.handleDeletePlayer.bind(this)}
                    />
                ),
                viewName: App.playersView
            });
        }
    }

    handlePlayGame() {
        // actually the game, not the matchup view
        this.setState({
            view: <MatchupsView matchups={this.state.players} onSubmit={this.handleSubmitScore.bind(this)} />,
            viewName: App.matchupsView
        });
    }

    /* Handle server API requests */

    handleGetMatchups(players) {
        /*this.setState({view: <Circle size={50} />, viewName: App.loading});
        let request = new XMLHttpRequest();
        request.open('POST', '/api/matchups/', true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let matchups = JSON.parse(request.responseText);*/
                this.setState({
                    view: (
                        <MatchupsView
                            matchups={this.state.players}
                            onSubmit={this.handlePlayGame.bind(this)}
                        />
                    ),
                    viewName: App.matchupsView
                });
           /* } else {
                // display error message
                // don't change view
            }
        };
        request.send(players);*/
    }

    handleCreatePlayer(player) {
        WebApiClient.post('/api/player/', player);
    }

    handleUpdatePlayer(player) {
        if (player) {
            WebApiClient.put('/api/player/', player);
        }
    }

    handleDeletePlayer(player) {
        if (player) {
            WebApiClient.delete('/api/player/player.id');
        }
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
                {this.state.view}
            </div>
        );
    }
}