import React from 'react';
import {FormGroup, FormControl, ControlLabel, ButtonToolbar, Button} from 'react-bootstrap';
import '../player_form/player-form.scss';

export default class UpdatePlayerView extends React.Component {

    static propTypes = {
        player: React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        }).isRequired,
        onCancel: React.PropTypes.func.isRequired,
        onUpdate: React.PropTypes.func.isRequired
    };

    state = {
        id: this.props.player.id,
        firstName: this.props.player.firstName,
        lastName: this.props.player.lastName,
        nickname: this.props.player.nickname
    };

    componentWillReceiveProps() {
        this.setState({
            id: this.props.player.id,
            firstName: this.props.player.firstName,
            lastName: this.props.player.lastName,
            nickname: this.props.player.nickname
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

    handleUpdate() {
        this.props.onUpdate({
            id: this.state.id,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            nickname: this.state.nickname
        });
    }

    render() {
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
                <ButtonToolbar>
                    <Button
                        className='player-form-element left-btn'
                        onClick={this.props.onCancel}>
                        Cancel
                    </Button>
                    <Button
                        bsStyle='warning'
                        className='player-form-element'
                        onClick={this.handleUpdate.bind(this)}>
                        Update Player
                    </Button>
                </ButtonToolbar>
            </FormGroup>
        );
    }
}