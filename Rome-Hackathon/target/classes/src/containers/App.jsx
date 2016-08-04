import React from 'react';
import './app.scss';
import MockData from '../mocks/MockData.jsx';
import WebApiClient from '../ajax/WebApiClient.jsx';
import NavMenu from '../components/nav_menu/NavMenu.jsx';
import PlayersAtTheTable from '../components/players_at_the_table/PlayersAtTheTable.jsx';
import GamesView from '../components/games_view/GamesView.jsx';
import PlayersView from '../components/players_view/PlayersView.jsx';
import CreatePlayerView from '../components/create_player_view/CreatePlayerView.jsx';
import UpdatePlayerView from '../components/update_player_view/UpdatePlayerView.jsx';
import DeletePlayerView from '../components/delete_player_view/DeletePlayerView.jsx';

export default class App extends React.Component {

    static playersAtTheTableView = 'playersAtTheTableView';
    static gamesView = 'gamesView';
    static playersView = 'playersView';
    static createPlayerView = 'createPlayerView';
    static updatePlayerView = 'updatePlayerView';
    static deletePlayerView = 'deletePlayerView';

    state = {view: null, viewName: null, players: null, games_view: null};

    // test state -- remove for production
    constructor(props) {
        super(props);
        this.players = MockData.getPlayers();
        let games = MockData.getGames();
        this.state = {
            view: (
                <PlayersAtTheTable
                    players={JSON.parse(JSON.stringify(this.players))}
                    onSubmit={this.handlePlayersAtTheTable.bind(this)}
                />
            ),
            viewName: App.playersAtTheTableView,
            players: this.players,
            games: games
        };
    }

    componentDidMount() {
        //this.fetchAllPlayers();  // production call
    }

    fetchAllPlayers() {
        // this should actually get all the content we need to start with
        // i.e. games_view, players, etc...
        let players = WebApiClient.get('/api/player/');
        if (players !== null) {
            this.setState({
                view: (
                    <PlayersAtTheTable
                        players={JSON.parse(JSON.stringify(players))}
                        onSubmit={this.handlePlayersAtTheTable.bind(this)}
                    />
                ),
                viewName: App.playersAtTheTableView,
                players: players,
                games: MockData.getGames()
            });
        }
    }

    /* Handle requests to toggle view */

    handlePlayersAtTheTableView() {
        if (this.state.viewName !== App.playersAtTheTableView) {
            this.setState({
                view: (
                    <PlayersAtTheTable
                        players={JSON.parse(JSON.stringify(this.state.players))}
                        onSubmit={this.handlePlayersAtTheTable.bind(this)}
                    />
                ),
                viewName: App.playersAtTheTableView
            });
        }
    }

    handleShowGamesView() {
        if (this.state.viewName !== App.gamesView) {
            this.setState({
                view: <GamesView games={this.state.games} />,
                viewName: App.gamesView
            });
        }
    }

    handleShowPlayersView() {
        if (this.state.viewName !== App.playersView) {
            this.setState({
                view: <PlayersView players={this.state.players} />,
                viewName: App.playersView
            });
        }
    }

    handleShowCreatePlayerView() {
        if (this.state.viewName !== App.createPlayerView) {
            this.setState({
                view: <CreatePlayerView onSubmit={this.handleCreatePlayer} />,
                viewName: App.createPlayerView
            });
        }
    }

    handleShowUpdatePlayerView() {
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

    handleShowDeletePlayerView() {
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

    /* Handle requests to modify data */

    handlePlayersAtTheTable(players) {
        console.log(players);
    }

    handleCreatePlayer(firstName, lastName, nickname) {
        // validation should occur in PlayerForm - post the created player
        let body = {firstName: firstName, lastName: lastName, nickname: nickname};
        WebApiClient.post('/api/player/', body);
    }

    handleUpdatePlayer(player) {
        if (player) {
            console.log('handling update');
        }
    }

    handleDeletePlayer(player) {
        if (player) {
            console.log('handling delete');
        }
    }

    render() {
        this.handleCreatePlayer("Craig", "Walker", "cwalk");
        if (!this.state.view) {
            return <div>Loading data...</div>;
        }
        return (
            <div className='app'>
                <div id='app-menu'>
                    <NavMenu
                        onPlayersAtTheTableView={this.handlePlayersAtTheTableView.bind(this)}
                        onGamesView={this.handleShowGamesView.bind(this)}
                        onPlayersView={this.handleShowPlayersView.bind(this)}
                        onCreatePlayerView={this.handleShowCreatePlayerView.bind(this)}
                        onUpdatePlayerView={this.handleShowUpdatePlayerView.bind(this)}
                        onDeletePlayerView={this.handleShowDeletePlayerView.bind(this)}
                    />
                </div>
                <div id='app-content'>
                    {this.state.view}
                </div>
            </div>
        );
    }

}