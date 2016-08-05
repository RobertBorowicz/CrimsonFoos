import React from 'react';
import {SplitButton, MenuItem, FormGroup, FormControl, ControlLabel, Button} from 'react-bootstrap';
import ModifyPlayerAlert from '../alerts/ModifyPlayerAlert.jsx';
import './delete-player-view.scss';
import '../player_form/player-form.scss';

export default class DeletePlayerView extends React.Component {

    static propTypes = {
        players: React.PropTypes.array.isRequired,
        onSubmit: React.PropTypes.func.isRequired
    };

    state = {
        selected: false,
        id: null,
        firstName: null,
        lastName: null,
        nickname: null,
        alertVisible: false
    };

    componentWillReceiveProps() {
        this.setState({
            selected: false,
            id: null,
            firstName: null,
            lastName: null,
            nickname: null,
            alertVisible: false
        });
    }

    handlePlayerSelection(eventKey) {
        let player = this.props.players[eventKey];
        this.setState({
            selected: true,
            id: player.id,
            firstName: player.firstName,
            lastName: player.lastName,
            nickname: player.nickname
        });
    }

    handleFirstNameChange(event) {
        this.setState({firstName: event.target.value});
    }

    handleLastNameChange(event) {
        this.setState({lastName: event.target.value});
    }

    handleNicknameChange(event) {
        this.setState({nickname: event.target.value});
    }

    handleAlertShow() {
        this.setState({alertVisible: true});
    }

    handleSubmitConfirmation(confirm) {
        this.setState({alertVisible: false, selected: false});
        if (confirm) {
            this.props.onSubmit({
                id: this.state.id,
                firstName: this.state.firstName,
                lastName: this.state.lastName,
                nickname: this.state.nickname
            });
        }
    }

    getPlayerMenuItems() {
        if (!this.props.players) {
            return [];
        }

        let items = [];
        this.props.players.forEach((player, i) => {
            items.push(
                <MenuItem eventKey={i} key={i}>
                    {player.firstName + ' ' + player.lastName}
                </MenuItem>
            );
            if (i < (this.props.players.length - 1)) {
                items.push(<MenuItem divider key={this.props.players.length + i}/>);
            }
        });
        return items;
    }

    getPlayerForm() {
        return (
            <FormGroup className='player-form-container'>
                <div className='player-form-group'>
                    <ControlLabel>First Name</ControlLabel>
                    <FormControl
                        type='text'
                        className='player-form-element'
                        value={this.state.firstName}
                        onChange={this.handleFirstNameChange.bind(this)}
                    />
                </div>
                <div className='player-form-group'>
                    <ControlLabel>LastName</ControlLabel>
                    <FormControl
                        type='text'
                        className='player-form-element'
                        value={this.state.lastName}
                        onChange={this.handleLastNameChange.bind(this)}
                    />
                </div>
                <div className='player-form-group'>
                    <ControlLabel>Nickname</ControlLabel>
                    <FormControl
                        type='text'
                        className='player-form-element'
                        value={this.state.nickname}
                        onChange={this.handleNicknameChange.bind(this)}
                    />
                </div>
                <Button
                    bsStyle='danger'
                    className='player-form-element'
                    onClick={this.handleAlertShow.bind(this)}>
                    Delete Player
                </Button>
            </FormGroup>
        );
    }

    render() {
        let view;
        if (this.state.alertVisible) {
            view = (
                <ModifyPlayerAlert
                    action='Deletions'
                    alertStyle='danger'
                    firstName={this.state.firstName}
                    lastName={this.state.lastName}
                    nickname={this.state.nickname}
                    onConfirm={this.handleSubmitConfirmation.bind(this)}
                />
            );
        } else {
            view = (
                <form className='outer-container'>
                    <SplitButton
                        title='Select a player to delete'
                        id='update'
                        onSelect={this.handlePlayerSelection.bind(this)}>
                        {this.getPlayerMenuItems()}
                    </SplitButton>
                    {this.state.selected && this.props.players ? this.getPlayerForm() : null}
                </form>
            );
        }
        return view;
    }
}