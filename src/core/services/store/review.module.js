const state = {
  todos: [],
};
const getters = {
  totalTodoCount(state) {
    return state.todos.length;
  },
  completedTodoCount(state) {
    return state.todos.filter((todo) => {
      return todo.completed === true;
    }).length;
  },
  uncompletedTodoCount(state) {
    return state.todos.filter((todo) => {
      return todo.completed === false;
    }).length;
  },
};
const mutations = {
  ADD_TODO(state, todoItem) {
    state.todos.push(todoItem);
  },
  DELETE_TODO(state, todo) {
    state.todos.splice(state.todos.indexOf(todo), 1);
  },
  UPDATE_TODO_STATUS(state, todo) {
    state.todos = state.todos.map((todoItem) => {
      if (todoItem === todo) {
        return {
          title: todo.title,
          completed: !todo.completed,
        };
      }
      return todoItem;
    });
  },
};
const actions = {
  addTodo({ commit }, todoItem) {
    commit('ADD_TODO', todoItem);
  },
  deleteTodo({ commit }, todo) {
    commit('DELETE_TODO', todo);
  },
  updateTodoStatus({ commit }, todo) {
    commit('UPDATE_TODO_STATUS', todo);
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
