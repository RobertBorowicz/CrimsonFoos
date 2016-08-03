import React from 'react';
import {Navbar, Nav, NavItem} from 'react-bootstrap';
import './nav-menu.scss';

export default class NavMenu extends React.Component {

    static propTypes = {
        onPlayersAtTheTableView: React.PropTypes.func.isRequired,
        onGamesView: React.PropTypes.func.isRequired,
        onPlayersView: React.PropTypes.func.isRequired,
        onCreatePlayerView: React.PropTypes.func.isRequired,
        onUpdatePlayerView: React.PropTypes.func.isRequired,
        onDeletePlayerView: React.PropTypes.func.isRequired
    }

    state = {show: false};

    handleToggle() {
        this.setState({show: !this.state.show});
    }

    handleSelection(callback) {
        this.setState({show: false});
        callback();
    }

    render() {
        return (
            <Navbar fluid inverse className="nav-menu" expanded={this.state.show} onToggle={this.handleToggle.bind(this)}>
                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="#"><em>CRIMSON <small>Foosball Tracker</small></em></a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav pullRight key={1}>
                        <NavItem
                            onClick={() => this.handleSelection(this.props.onPlayersAtTheTableView)}>
                            Players At The Table
                        </NavItem>
                        <NavItem
                            onClick={() => this.handleSelection(this.props.onGamesView)}>
                            View Games
                        </NavItem>
                        <NavItem
                            onClick={() => this.handleSelection(this.props.onPlayersView)}>
                            View Players
                        </NavItem>
                        <NavItem
                            onClick={() => this.handleSelection(this.props.onCreatePlayerView)}>
                            Create Player
                        </NavItem>
                        <NavItem
                            onClick={() => this.handleSelection(this.props.onUpdatePlayerView)}>
                            Update Player
                        </NavItem>
                        <NavItem
                            onClick={() => this.handleSelection(this.props.onDeletePlayerView)}>
                            Delete Player
                        </NavItem>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }
}