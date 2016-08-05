import React from 'react';
import {ListGroup, ListGroupItem, Button} from 'react-bootstrap';
import FaChevronLeft from 'react-icons/lib/fa/chevron-left';
import FaChevronRight from 'react-icons/lib/fa/chevron-right';
import './matchups-view.scss';

export default class MatchupsView extends React.Component {

    static propTypes = {
        matchups: React.PropTypes.array.isRequired,
        onSubmit: React.PropTypes.func.isRequired
    };

    state = {index: 0};

    handleToggle(value) {
        let index = this.state.index + value;
        if (index >= 0 && index < this.props.matchups.length) {
            this.setState({index: index});
        }
    }

    handleSubmit() {
        this.props.onSubmit(this.props.matchups[this.state.index]);
    }

    getItem() {
        let left = this.state.index === 0 ? ' disabled' : '';
        let right = this.state.index + 1 === this.props.matchups.length ? ' disabled' : '';
        return (
            <div className='table-group'>
                <div className='group-element'>
                    <a className='toggle' href='#' onClick={() => this.handleToggle(-1)}>
                        <FaChevronLeft className={'icon' + left} />
                    </a>
                </div>
                <div className='group-element'>
                    <ListGroup className='table-panel'>
                        <ListGroupItem className='blue-team'>Blue Team</ListGroupItem>
                        <ListGroupItem>Table Image Here</ListGroupItem>
                        <ListGroupItem className='red-team'>Red Team</ListGroupItem>
                    </ListGroup>
                </div>
                <div className='group-element'>
                    <a className='toggle' href='#' onClick={() => this.handleToggle(1)}>
                        <FaChevronRight className={'icon' + right} />
                    </a>
                </div>
            </div>
        );
    }

    render() {
        return (
            <div>
                {this.getItem()}
                <div className='matchup-text'>
                    <h1 className='matchup-label'>Matchup {this.state.index}</h1>
                    <p>NameA {this.state.index}</p>
                    <p>NameB {this.state.index}</p>
                    <p className='vs-tag'><b>vs</b></p>
                    <p>NameX {this.state.index}</p>
                    <p>NameY {this.state.index}</p>
                </div>
            </div>
        );
    }
}