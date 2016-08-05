import React from 'react';
import {Panel, ListGroup, ListGroupItem, Button} from 'react-bootstrap';
import './new-game.scss';

export default class NewGame extends React.Component {

    static propTypes = {
        players: React.PropTypes.arrayOf(React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        })).isRequired,
        onSubmit: React.PropTypes.func.isRequired
    };

    state = {selected: []};

    componentWillReceiveProps() {
        this.setState({selected: []});
    }

    handleAddPlayer(player) {
        let selected = this.state.selected;
        selected.push(player);
        this.setState({selected: selected});
    }

    handleRemovePlayer(player) {
        let selected = this.state.selected;
        selected.splice(selected.indexOf(player), 1);
        this.setState({selected: selected});
    }

    handleSubmit() {
        console.log(this.state.players);
        this.props.onSubmit(this.state.selected);
    }

    getSelectedPlayers() {
        let selectedPlayers = this.state.selected.map((player, i) => {
            return (
                <p key={player.id + 'selected'}>{(i + 1) + '. ' + player.nickname}</p>
            );
        });
        return <ListGroup className='selected-players-container'>{selectedPlayers}</ListGroup>;
    }

    getMatchupsButton() {
        if (this.state.selected.length < 4) {
            return null;
        }
        return (
            <Button bsStyle='success' className='matchups-button'
                onClick={this.handleSubmit.bind(this)}>
                Get Matchups
            </Button>
        );
    }

    getPlayersList() {
        let players = this.props.players.map(player => {
            let cName, callback, btnText;
            if (this.state.selected.indexOf(player) === -1) {
                cName = 'unselected-list-item';
                callback = () => this.handleAddPlayer(player);
                btnText = '+';
            } else {
                cName = 'selected-list-item';
                callback = () => this.handleRemovePlayer(player);
                btnText = 'x';
            }
            let btnClass = 'item-selection-button';
            return (
                <ListGroupItem className={cName} key={player.id + cName}>
                    {player.nickname}
                    <Button
                        className={btnClass + ' ' + btnClass + '-' + cName}
                        bsSize='small'
                        onClick={callback}>
                        {btnText}
                    </Button>
                </ListGroupItem>
            );
        });
        return <ListGroup className='players-list'>{players}</ListGroup>;
    }

    // this is a test
    render() {
        return (
            <div>
                <div className='player-selection-outer-container'>
                    <h4 className='header'>Players at the table:</h4>
                    <hr/>
                    <div className='selected-panel'>
                        {this.getSelectedPlayers()}
                        {this.getMatchupsButton()}
                    </div>
                </div>
                {this.getPlayersList()}
            </div>
        );
    }
}