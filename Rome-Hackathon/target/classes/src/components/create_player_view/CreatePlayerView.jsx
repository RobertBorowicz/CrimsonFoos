import React from 'react';
import {FormGroup, FormControl, Button} from 'react-bootstrap';
import '../player_form/player-form.scss';

export default class CreatePlayerView extends React.Component {

    static propTypes = {
      onSubmit: React.PropTypes.func.isRequired
    };

    state = {firstName: '', lastName: '', nickname: ''};

    handleFirstNameChange(event) {
        this.setState({firstName: event.target.value});
    }

    handleLastNameChange(event) {
        this.setState({lastName: event.target.value});
    }

    handleNicknameChange(event) {
        this.setState({nickname: event.target.value});
    }

    handleSubmit() {
        this.props.onSubmit({
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            nickname: this.state.nickname
        });
    }

    render() {
        return (
            <form className='outer-container'>
                <FormGroup>
                    <div className='player-form-group'>
                        <FormControl
                            type='text'
                            className='player-form-element'
                            placeholder='Fist Name'
                            value={this.state.firstName}
                            onChange={this.handleFirstNameChange.bind(this)}
                        />
                    </div>
                    <div className='player-form-group'>
                        <FormControl
                            type='text'
                            className='player-form-element'
                            placeholder='Last Name'
                            value={this.state.lastName}
                            onChange={this.handleLastNameChange.bind(this)}
                        />
                    </div>
                    <div className='player-form-group'>
                        <FormControl
                            type='text'
                            className='player-form-element'
                            placeholder='Nickname'
                            value={this.state.nickname}
                            onChange={this.handleNicknameChange.bind(this)}
                        />
                    </div>
                    <Button
                        bsStyle='primary'
                        className='player-form-element'
                        onClick={this.handleSubmit.bind(this)}>
                        Create Player
                    </Button>
                </FormGroup>
            </form>
        );
    }
}