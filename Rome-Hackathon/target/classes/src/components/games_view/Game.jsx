import React from 'react';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {Grid, Row, Col} from 'react-bootstrap';
import './games-view.scss';

export default class Game extends React.Component {

    static propTypes = {
        game: React.PropTypes.shape({
            red: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            }),
            blue: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            })
        }).isRequired
    };

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    render() {
        let winner = (this.props.game.red.goals > this.props.game.blue.goals
                     ? 'winner-red-team' : 'winner-blue-team');
        return (
            <Grid className={'single-game ' + winner}>
                <Row>
                    <Col xs={4} md={4} className='team left'>
                        <p>{this.props.game.red.players[0]}</p>
                        <p>{this.props.game.red.players[1]}</p>
                        <p>{this.props.game.red.goals}</p>
                    </Col>
                    <Col xs={3} md={3} className='center'>VS.</Col>
                    <Col xs={4} md={4} className='team right'>
                        <p>{this.props.game.blue.players[0]}</p>
                        <p>{this.props.game.blue.players[1]}</p>
                        <p>{this.props.game.blue.goals}</p>
                    </Col>
                </Row>
            </Grid>
        );
    }
}