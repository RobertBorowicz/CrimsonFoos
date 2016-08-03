import React from 'react';
import DeepEqual from '../../utils/DeepEqual.jsx';
import Game from './Game.jsx';
import './games-view.scss';

export default class Games extends React.Component {

    static propTypes = {
        games: React.PropTypes.arrayOf(React.PropTypes.shape({
            red: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            }),
            blue: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            })
        }))
    };

    static defaultProps = {games: null};

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    render() {
        var games = null;
        if (this.props.games) {
            games = this.props.games.map((game, i) => {
                return <Game game={game} key={i}/>;
            });
        }
        return <div className='games-list'>{games}</div>;
    }
}