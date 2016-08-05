import React from 'react';
import {Alert, Button} from 'react-bootstrap';
import './delete-player-view.scss';
import '../player_form/player-form.scss';

export default class DeletePlayerView extends React.Component {

    static propTypes = {
        player: React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        }).isRequired,
        onCancel: React.PropTypes.func.isRequired,
        onDelete: React.PropTypes.func.isRequired
    };

    render() {
        return (
            <Alert bsStyle='danger' className='alert-container'>
                <h2>Delete the following player?</h2>
                <div className='player-info-group'>
                    <p>ID: {this.props.player.id}</p>
                    <p>First Name: {this.props.player.firstName}</p>
                    <p>Last Name: {this.props.player.lastName}</p>
                    <p>Nickname: {this.props.player.nickname}</p>
                </div>
                <div className='alert-button-group'>
                    <Button
                        className='left-button'
                        onClick={this.props.onCancel}>
                        Cancel
                    </Button>
                    <Button
                        bsStyle='danger'
                        onClick={() => this.props.onDelete(this.props.player)}>
                        Confirm
                    </Button>
                </div>
            </Alert>
        );
    }
}