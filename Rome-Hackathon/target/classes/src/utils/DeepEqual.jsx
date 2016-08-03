import equal from 'deep-equal';

export default class ReactUtil {
    static updateIfPropsOrStateChanged(nextProps, nextState) {
        return !equal(this.props, nextProps) || !equal(this.state, nextState);
    }
}