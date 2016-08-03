import React from 'react';
import {Panel, ListGroup, ListGroupItem, Button} from 'react-bootstrap';

export default class PlayersAtTheTable extends React.Component {

    static propTypes = {
        players: React.PropTypes.arrayOf(React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        })).isRequired,
        onSubmit: React.PropTypes.func.isRequired
    };

    state = {selected: [], unselected: this.props.players};

    componentWillReceiveProps(nextProps) {
        this.setState({selected: [], unselected: nextProps.players});
    }

    handleAddSelectedPlayer(player) {
        let selected = this.state.selected;
        selected.push(player);
        let unselected = this.state.unselected;
        unselected.splice(this.getIndexOfPlayer(player, unselected), 1);
        this.setState({selected: selected, unselected: unselected});
    }

    handleRemoveSelectedPlayer(player) {
        let selected = this.state.selected;
        selected.splice(this.getIndexOfPlayer(player, selected), 1);
        let unselected = this.state.unselected;
        unselected.push(player);
        this.setState({selected: selected, unselected: unselected});
    }

    getIndexOfPlayer(player, array) {
        for (let i = 0; i < array.length; i++) {
            if (player.id === array[i].id) {
                return i;
            }
        }
        return -1;
    }

    handleSubmit(players) {
        this.props.onSubmit(players);
    }

    getSelectedPlayers() {
        let selectedPlayers = this.state.selected.map(player => {
            return (
                <ListGroupItem className='selected-player' key={player.id + 'selected'}
                    onClick={() => this.handleRemoveSelectedPlayer(player)}>
                    {player.nickname}
                </ListGroupItem>
            );
        });
        return <ListGroup className='selected-players-container'>{selectedPlayers}</ListGroup>;
    }

    getMatchupsButton() {
        if (this.state.selected.length < 4) {
            return null;
        }

        return (
            <Button bsStyle='primary' className='matchups-button'
                onClick={() => this.handleSubmit(this.state.selected)}>
                Get Matchups
            </Button>
        );
    }

    getUnselectedPlayers() {
        let unselectedPlayers = this.state.unselected.map(player => {
            return (
                <ListGroupItem className='unselected-player' key={player.id + 'unselected'}
                    onClick={() => this.handleAddSelectedPlayer(player)}>
                    {player.nickname}
                </ListGroupItem>
            );
        });
        return <ListGroup className='unselected-players-container'>{unselectedPlayers}</ListGroup>;
    }

    render() {
        return (
            <Panel className='player-selection-outer-container'>
                <Panel className='selected-panel' header='Players At the Table'>
                    {this.getSelectedPlayers()}
                    {this.getMatchupsButton()}
                </Panel>
                <Panel className='unselected-panel' header='Available Players'>
                    {this.getUnselectedPlayers()}
                </Panel>
            </Panel>
        );
    }
}