import React from 'react';
import {FormGroup, FormControl, ControlLabel, Button} from 'react-bootstrap';
import './player-form.scss';

export default class PlayerForm extends React.Component {

    static propTypes = {
        player: React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        }),
        buttonText: React.PropTypes.string,
        buttonStyle: React.PropTypes.string,
        onSubmit: React.PropTypes.func.isRequired
    };

    static defaultProps = {
        player: {
            id: 0,
            firstName: '',
            lastName: '',
            nickname: ''
        },
        buttonText: 'Submit',
        buttonStyle: 'default'
    }

    state = {
        id: this.props.player.id,
        firstName: this.props.player.firstName,
        lastName: this.props.player.lastName,
        nickname: this.props.player.nickname
    };

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
            id: this.state.id,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            nickname: this.state.nickname
        });
    }

    render() {
        return (
            <form className='player-form'>
                <FormGroup>
                    <div className='player-form-group'>
                        <ControlLabel className='player-form-label'>First Name</ControlLabel>
                        <FormControl
                            type={'text'}
                            className='player-form-element'
                            value={this.state.firstName}
                            onChange={this.handleFirstNameChange.bind(this)}
                        />
                    </div>
                    <div className='player-form-group'>
                        <ControlLabel className='player-form-label'>Last Name</ControlLabel>
                        <FormControl
                            type={'text'}
                            className='player-form-element'
                            value={this.state.lastName}
                            onChange={this.handleLastNameChange.bind(this)}
                        />
                    </div>
                    <div className='player-form-group'>
                        <ControlLabel className='player-form-label'>Nickname</ControlLabel>
                        <FormControl
                            type={'text'}
                            className='player-form-element'
                            value={this.state.nickname}
                            onChange={this.handleNicknameChange.bind(this)}
                        />
                    </div>
                    <Button
                        bsStyle={this.props.buttonStyle}
                        bsSize='large'
                        className='player-form-element player-form-button'
                        onClick={this.handleSubmit.bind(this)}>
                        {this.props.buttonText}
                    </Button>
                </FormGroup>
            </form>
        );
    }
}