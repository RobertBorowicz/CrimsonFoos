import React from 'react';
import {Alert, Button} from 'react-bootstrap';
import './modify-player-alert.scss';

export default class ModifyPlayerAlert extends React.Component {

    static propTypes = {
        action: React.PropTypes.string.isRequired,
        alertStyle: React.PropTypes.string.isRequired,
        firstName: React.PropTypes.string.isRequired,
        lastName: React.PropTypes.string.isRequired,
        nickname: React.PropTypes.string.isRequired,
        onConfirm: React.PropTypes.func.isRequired
    }

    handleConfirm(confirm) {
        this.props.onConfirm(confirm);
    }

    render() {
        return (
            <Alert bsStyle={this.props.alertStyle} className='alert-container'>
                <h2>{this.props.action} are permanent!</h2>
                <h3>Please confirm this action</h3>
                <div className='player-info-group'>
                    <h4>Player Info:</h4>
                    <p>First Name: {this.props.firstName}</p>
                    <p>Last Name: {this.props.lastName}</p>
                    <p>Nickname: {this.props.nickname}</p>
                </div>
                <div className='alert-button-group'>
                    <Button
                        className='left-button'
                        onClick={() => this.handleConfirm(false)}>
                        Cancel
                    </Button>
                    <Button
                        bsStyle={this.props.alertStyle}
                        onClick={() => this.handleConfirm(true)}>
                        Confirm
                    </Button>
                </div>
            </Alert>
        );
    }
}