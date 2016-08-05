import React from 'react';
import {DropdownButton, MenuItem, Table} from 'react-bootstrap';
import DeepEqual from '../../utils/DeepEqual.jsx';

export default class StatsView extends React.Component {

    // add win/loss streak, shutouts, etc... if we have the data
    static propTypes = {
        players: React.PropTypes.arrayOf(React.PropTypes.shape({
            id: React.PropTypes.number,
            firstName: React.PropTypes.string,
            lastName: React.PropTypes.string,
            nickname: React.PropTypes.string,
            stats: React.PropTypes.shape({
                games: React.PropTypes.number,
                wins: React.PropTypes.number,
                losses: React.PropTypes.number
            })
        })).isRequired,
        teams: React.PropTypes.arrayOf(React.PropTypes.shape({
            id: React.PropTypes.number,
            players: React.PropTypes.array,
            stats: React.PropTypes.shape({
                games: React.PropTypes.number,
                wins: React.PropTypes.number,
                losses: React.PropTypes.number
            })
        })).isRequired
    };

    state = {teamStats: false};

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    handleSelection(value) {
        if (this.state.teamStats !== value) {
            this.setState({teamStats: value});
        }
    }

    getHeaders() {
        return ['Rank', 'Player', 'Pct', 'G', 'W', 'L'].map(header => {
            return <th key={header + 'statsView'}>{header}</th>;
        });
    }

    /* change rank to reflect whichever category determines it */
    getRows(elements) {
        if (!elements) {
            return [];
        }
        let ranked = this.getRankedElements(elements);
        let rows = ranked.map((element, i) => {
            let name = this.state.teamStats ? element.players[0].nickname + ' & ' + element.players[1].nickname : element.nickname;
            return (
                <tr key={element.id}>
                    <td>{i + 1}</td>
                    <td>{name}</td>
                    <td>{element.stats.pct}</td>
                    <td>{element.stats.gamesPlayed}</td>
                    <td>{element.stats.wins}</td>
                    <td>{element.stats.losses}</td>
                </tr>
            );
        });
        return <tbody>{rows}</tbody>;
    }

    getRankedElements(elements) {
        var ranked = [];
        elements.forEach(element => {
            element.stats.pct = Math.round(element.stats.wins / element.stats.gamesPlayed * 1000) / 1000;
            ranked.push(element);
            for (let i = ranked.length - 1; i >= 1; i--) {
                if (ranked[i].stats.pct > ranked[i - 1].stats.pct) {
                    let temp = ranked[i - 1];
                    ranked[i - 1] = ranked[i];
                    ranked[i] = temp;
                } else {
                    break;
                }
            }
        });
        return ranked;
    }

    render() {
        return (
            <div>
                <DropdownButton id='stats' title={this.state.teamStats ? 'Team' : 'Individual'}>
                    <MenuItem onClick={() => this.handleSelection(false)}>Individual</MenuItem>
                    <MenuItem onClick={() => this.handleSelection(true)}>Team</MenuItem>
                </DropdownButton>
                <Table striped bordered condensed>
                    <thead>
                        <tr>
                            {this.getHeaders()}
                        </tr>
                    </thead>
                    {this.getRows(this.state.teamStats ? this.props.teams : this.props.players)}
                </Table>
            </div>
        );
    }
}