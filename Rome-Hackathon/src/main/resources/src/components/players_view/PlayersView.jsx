import React from 'react';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {Table} from 'react-bootstrap';
import FaPencil from 'react-icons/lib/fa/pencil';
import FaBan from 'react-icons/lib/fa/ban';
import CreatePlayerView from './create_player_view/CreatePlayerView.jsx';
import UpdatePlayerView from './update_player_view/UpdatePlayerView.jsx';
import DeletePlayerView from './delete_player_view/DeletePlayerView.jsx';
import './players-view.scss';

export default class PlayersView extends React.Component {

    static propTypes = {
        players: React.PropTypes.arrayOf(React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string
        })).isRequired,
        onCreate: React.PropTypes.func.isRequired,
        onUpdate: React.PropTypes.func.isRequired,
        onDelete: React.PropTypes.func.isRequired
    };

    state = {
        players: this.props.players,
        view: this.getTable()
    };

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    handleCreateView() {
        this.setState({
            view: <CreatePlayerView onSubmit={this.props.onCreate} />
        });
    }

    handleUpdateView(index) {
        this.setState({
            view: (
                <UpdatePlayerView
                    player={this.state.players[index]}
                    onCancel={this.handleCancel.bind(this)}
                    onUpdate={this.handleUpdate.bind(this)}
                />
            )
        });
    }

    handleDeleteView(index) {
        this.setState({
            view: (
                <DeletePlayerView
                    player={this.state.players[index]}
                    onCancel={this.handleCancel.bind(this)}
                    onDelete={this.handleDelete.bind(this)}
                />
            )
        });
    }

    handleCreate(firstName, lastName, nickname) {
        this.props.onCreate(firstName, lastName, nickname);
        this.setState({view: this.getTable()});
    }

    handleCancel() {
        this.setState({view: this.getTable()});
    }

    handleUpdate(player) {
        this.props.onUpdate(player);
        this.setState({view: this.getTable});
    }

    handleDelete(player) {
        this.props.onDelete(player);
        this.setState({view: this.getTable()});
    }

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
        return ['ID', 'First Name', 'Last Name', 'Nickname', 'Edit', 'Delete'].map(header => {
            return <th key={header + 'playersView'}>{header}</th>;
        });
    }

    getRows() {
        let rows = this.props.players.map((player, i) => {
            return (
                <tr key={player.id + 'playersView'}>
                    <td>{player.id}</td>
                    <td>{player.firstName}</td>
                    <td>{player.lastName}</td>
                    <td>{player.nickname}</td>
                    <td>
                        <a href='#' className='center' onClick={() => this.handleUpdateView(i)}>
                            <FaPencil />
                        </a>
                    </td>
                    <td>
                        <a href='#' className='center' onClick={() => this.handleDeleteView(i)}>
                            <FaBan className='remove'/>
                        </a>
                    </td>
                </tr>
            );
        });
        return <tbody>{rows}</tbody>;
    }

    render() {
        return this.state.view;
    }
}