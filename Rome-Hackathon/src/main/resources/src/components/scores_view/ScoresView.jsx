import React from 'react';
import moment from 'moment';
import ReactDatePicker from 'react-datepicker';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {Panel, Button} from 'react-bootstrap';
import FaChevronLeft from 'react-icons/lib/fa/chevron-left';
import FaChevronRight from 'react-icons/lib/fa/chevron-right';
import GameScore from './GameScore.jsx';
import './scores-view.scss';
import './game-score.scss';
import 'react-datepicker/dist/react-datepicker.css';

export default class ScoresView extends React.Component {

    static propTypes = {
        games: React.PropTypes.arrayOf(React.PropTypes.shape({
            red: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            }),
            blue: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            }),
            date: React.PropTypes.string.isRequired
        }))
    };

    static defaultProps = {games: null};

    state = {datePicked: moment()};

    componentWillReceiveProps() {
        this.setState({datePicked: moment()});
    }

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    handleDateChange(date) {
        if (date && date.isValid() && !date.isSame(this.state.datePicked, 'day')) {
            this.setState({datePicked: date});
        }
    }

    handleGoToYesterday() {
        let yesterday = this.state.datePicked.clone().subtract(1, 'days');
        this.setState({datePicked: yesterday});
    }

    handleGoToTomorrow() {
        let tomorrow = this.state.datePicked.clone().add(1, 'days');
        if (tomorrow.isSameOrBefore(moment(), 'day')) {
            this.setState({datePicked: tomorrow});
        }
    }

    getDateFilteredGames() {
        if (!this.props.games) {
            return [];
        }
        let games = [];
        this.props.games.forEach((game, i) => {
            if (this.state.datePicked.isSame(moment(game.date), 'day')) {
                games.push(<GameScore key={game.date + '' + i} game={game} />);
            }
        });
        return games;
    }

    render() {
        let games = this.getDateFilteredGames();
        let right = this.state.datePicked.isSame(moment(), 'day') ? ' disabled' : '';
        return (
            <div className='container'>
                <div className='date-picker-toolbar'>
                    <span>
                        <a className='toggle' href='#' onClick={this.handleGoToYesterday.bind(this)}>
                            <FaChevronLeft className={'icon'} />
                        </a>
                        <ReactDatePicker
                            className='date-picker'
                            dateFormat='MM-DD-YYYY'
                            selected={this.state.datePicked}
                            maxDate={moment()}
                            onChange={this.handleDateChange.bind(this)}
                        />
                        <a className='toggle' href='#' onClick={this.handleGoToTomorrow.bind(this)}>
                            <FaChevronRight className={'icon' + right} />
                        </a>
                    </span>
                </div>
                {games}
            </div>
        );
    }
}