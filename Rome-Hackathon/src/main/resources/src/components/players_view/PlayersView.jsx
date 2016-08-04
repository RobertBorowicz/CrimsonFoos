import React from 'react';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {Table} from 'react-bootstrap';
import './players-view.scss';

export default class Players extends React.Component {

    static tableView = 'tableView';
    static createView = 'creatView';
    static updateView = 'updateView';
    static deleteView = 'deleteView';

    static propTypes = {
        players: React.PropTypes.arrayOf(React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        })).isRequired,
        onCreate: React.PropTypes.func.isRequired,
        onUpdate: React.PropTypes.func.isRequired,
        onDelete: React.PropTypes
    };

    state = {
        players: this.props.players,
        view: this.getTable(),
        viewName: Players.tableView
    };

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    getTable() {
        return(
            <Table striped bordered condensed>
                <thead>
                <tr>
                    {this.getHeaders()}
                </tr>
                </thead>
                {this.getRows()}
            </Table>
        );
    }

    getHeaders() {
        return ['ID', 'First Name', 'Last Name', 'Nickname'].map(header => {
            return <th key={header + 'playersView'}>{header}</th>;
        });
    }

    getRows() {
        let rows = this.props.players.map(player => {
            return (
                <tr key={player.id + 'playersView'}>
                    <td>{player.id}</td>
                    <td>{player.firstName}</td>
                    <td>{player.lastName}</td>
                    <td>{player.nickname}</td>
                </tr>
            );
        });
        return <tbody>{rows}</tbody>;
    }

    render() {
        return this.state.view;
    }
}