//remove default value from stratgies Map and replace it in one place by identity function
const identity = (x) => x;

export default (strategiesMap, initState) => (state = initState, action) =>
  (strategiesMap[action.type] ?? identity)(state, action);
