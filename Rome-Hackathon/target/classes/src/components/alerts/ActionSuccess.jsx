import React from 'react';
import {Alert, Button} from 'react-bootstrap';

export default class PlayerActionSuccess extends React.Component {

    static propTypes = {
        action: React.PropTypes.string.isRequired,
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
            <Alert bsStyle='success'>
                <h4>Player Successfully {this.props.action}!</h4>
                <p>First Name: {this.props.firstName}</p>
                <p>Last Name: {this.props.lastName}</p>
                <p>Nickname: {this.props.nickname}</p>
                <p>
                    <Button
                        bsStyle='success'
                        onClick={() => this.handleConfirm(false)}>
                        OK
                    </Button>
                </p>
            </Alert>
        );
    }
}