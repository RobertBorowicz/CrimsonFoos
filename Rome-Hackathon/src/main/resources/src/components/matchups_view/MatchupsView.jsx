import React from 'react';
import {ListGroup, ListGroupItem, Button} from 'react-bootstrap';
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

    getItem(i) {
        return (
            <div className='group-element'>
                <ListGroup className='table-panel'>
                    <ListGroupItem className='blue-team'>Blue Team {i}</ListGroupItem>
                    <ListGroupItem>Table Image Here</ListGroupItem>
                    <ListGroupItem className='red-team'>Red Team {i}</ListGroupItem>
                </ListGroup>
            </div>
        );
    }

    render() {
        return (
            <div>
                <div className='table-group'>
                    <div className='group-element'>
                        <Button
                            className='toggle'
                            onClick={() => this.handleToggle(-1)}>
                            {'<'}
                        </Button>
                    </div>
                    {this.getItem(this.state.index)}
                    <div className='group-element'>
                        <Button
                            className='toggle'
                            disabled={this.state.index === this.props.matchups.length}
                            onClick={() => this.handleToggle(1)}>
                            {'>'}
                        </Button>
                    </div>
                </div>
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