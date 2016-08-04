import React from 'react';
import './app.scss';
import {Circle} from 'better-react-spinkit';
import MockData from '../mocks/MockData.jsx';
import WebApiClient from '../ajax/WebApiClient.jsx';
import NavMenu from '../components/nav_menu/NavMenu.jsx';
import NewGame from '../components/new_game/NewGame.jsx';
import ScoresView from '../components/scores_view/ScoresView.jsx';
import PlayersView from '../components/players_view/PlayersView.jsx';
import CreatePlayerView from '../components/create_player_view/CreatePlayerView.jsx';
import UpdatePlayerView from '../components/update_player_view/UpdatePlayerView.jsx';
import DeletePlayerView from '../components/delete_player_view/DeletePlayerView.jsx';

export default class App extends React.Component {

    static newGameView = 'newGameView';
    static scoresView = 'scoresView';
    static playersView = 'playersView';
    static createPlayerView = 'createPlayerView';
    static updatePlayerView = 'updatePlayerView';
    static deletePlayerView = 'deletePlayerView';
    static matchupsView = 'matchupsView';
    static loading = 'loading';

    state = {view: null, viewName: null, players: null, games: null, matchups: null};

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
        if (this.state.viewName !== App.scoresView) {
            this.setState({
                view: <ScoresView games={this.state.games} />,
                viewName: App.scoresView
            });
        }
    }

    handlePlayersView() {
        if (this.state.viewName !== App.playersView) {
            this.setState({
                view: <PlayersView players={this.state.players} />,
                viewName: App.playersView
            });
        }
    }

    handleCreatePlayerView() {
        if (this.state.viewName !== App.createPlayerView) {
            this.setState({
                view: <CreatePlayerView onSubmit={this.handleCreatePlayer} />,
                viewName: App.createPlayerView
            });
        }
    }

    handleUpdatePlayerView() {
        if (this.state.viewName !== App.updatePlayerView) {
            this.setState({
                view: (
                    <UpdatePlayerView
                        players={this.state.players}
                        onSubmit={this.handleUpdatePlayer.bind(this)}
                    />
                ),
                viewName: App.updatePlayerView
            });
        }
    }

    handleDeletePlayerView() {
        if (this.state.viewName !== App.deletePlayerView) {
            this.setState({
                view: (
                    <DeletePlayerView
                        players={this.state.players}
                        onSubmit={this.handleDeletePlayer.bind(this)}
                    />
                ),
                viewName: App.deletePlayerView
            });
        }
    }

    /* Handle server API requests */

    handleGetMatchups(players) {
        this.setState({view: <Circle size={50} />, viewName: App.loading});
        let request = new XMLHttpRequest();
        request.open('POST', '/api/matchups/', true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = () => {
            if (request.status === 200) {
                let matchups = JSON.parse(request.responseText);
                this.setState({
                    view: <Matchups
                        matchups={matchups}
                        onSubmit={this.handlePlayGame.bind(this)}
                    />,
                    viewName: App.matchupsView
                });

            } else {
                // display error message
                // don't change view
            }
        };
        request.send(players);
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
        if (!this.state.view) {
            return <div>Loading data...</div>;
        }
        return (
            <div className='app'>
                <div id='app-menu'>
                    <NavMenu
                        onNewGameView={this.handleNewGameView.bind(this)}
                        onScoresView={this.handleScoresView.bind(this)}
                        onPlayersView={this.handlePlayersView.bind(this)}
                        onCreatePlayerView={this.handleCreatePlayerView.bind(this)}
                        onUpdatePlayerView={this.handleUpdatePlayerView.bind(this)}
                        onDeletePlayerView={this.handleDeletePlayerView.bind(this)}
                    />
                </div>
                <div id='app-content'>
                    {this.state.view}
                </div>
            </div>
        );
    }

}