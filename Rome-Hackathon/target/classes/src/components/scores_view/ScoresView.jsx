import React from 'react';
import moment from 'moment';
import ReactDatePicker from 'react-datepicker';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {Panel, Button} from 'react-bootstrap';
//import GameScore from './GameScore.jsx';
import './scores-view.scss';
import 'react-datepicker/dist/react-datepicker.css';

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

    render() {
        return (
            <Panel className='date-picker-toolbar'>
                <span>
                    <Button>Prev</Button>
                    <ReactDatePicker
                        className='date-picker'
                        dateFormat='MM-DD-YYYY'
                        selected={this.state.datePicked}
                        maxDate={moment()}
                        onChange={this.handleDateChange.bind(this)}
                    />
                    <Button>Next</Button>
                </span>
            </Panel>
        );
    }

    /*
    render() {
        var games = null;
        if (this.props.games) {
            games = this.props.games.map((game, i) => {
                return <GameScore game={game} key={i}/>;
            });
        }
        return <div className='games-list'>{games}</div>;
    }*/
}