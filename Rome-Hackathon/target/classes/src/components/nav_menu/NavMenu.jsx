import React from 'react';
import {Navbar, Nav, NavItem} from 'react-bootstrap';
import './nav-menu.scss';

export default class NavMenu extends React.Component {

    static propTypes = {
        onNewGameView: React.PropTypes.func.isRequired,
        onScoresView: React.PropTypes.func.isRequired,
        onStatsView: React.PropTypes.func.isRequired,
        onPlayersView: React.PropTypes.func.isRequired
    };

    state = {show: false};

    handleToggle() {
        this.setState({show: !this.state.show});
    }

    handleSelection(callback) {
        this.setState({show: false});
        callback();
    }

    /* Nav menu will change according to UX design */

    render() {
        return (
            <Navbar fluid inverse className="nav-menu" expanded={this.state.show}
                    onToggle={this.handleToggle.bind(this)}>
                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="#"><em>CRIMSON <small>Foosball Tracker</small></em></a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav pullRight key={1}>
                        <NavItem onClick={() => this.handleSelection(this.props.onNewGameView)}>
                            New Game
                        </NavItem>
                        <NavItem onClick={() => this.handleSelection(this.props.onScoresView)}>
                            Scores
                        </NavItem>
                        <NavItem onClick={() => this.handleSelection(this.props.onStatsView)}>
                            Stats
                        </NavItem>
                        <NavItem onClick={() => this.handleSelection(this.props.onPlayersView)}>
                            Players
                        </NavItem>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }
}