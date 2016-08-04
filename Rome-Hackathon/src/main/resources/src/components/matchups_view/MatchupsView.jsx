import React from 'react';

export default class MatchupsView extends React.Component {

    static propTypes = {
        matchups: React.PropTypes.object.isRequired,
        onSubmit: React.PropTypes.func.isRequired
    };

    state = {matchup: null};

    handleNext() {

    }

    handlePrevious() {

    }

    handleSubmit() {
        this.props.onSubmit(this.state.matchup);
    }

    render() {
        return (
            <div>Matchups go here</div>
        );
    }
}