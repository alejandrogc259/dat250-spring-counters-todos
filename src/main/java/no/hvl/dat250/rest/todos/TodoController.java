package no.hvl.dat250.rest.todos;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest-Endpoint for todos.
 */
@RestController
public class TodoController {
  private List<Todo> list = new ArrayList<>();

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  @PostMapping ("/todos")
  public Object createTodo(@RequestBody Todo todo){
    todo.setId((long) list.size());
    list.add(todo);
    return todo;
  }

  @GetMapping("/todos")
  public List<Todo> getTodos(){
    return list;
  }
  @GetMapping("/todos/{id}")
  public Object getTodo(@PathVariable("id") int id){
    for(int i = 0; i < list.size(); i++){
      Todo t = list.get(i);
      if(t.getId() == id) return t;
    }
    return "\"message\":\"" + String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id);
  }

  @PutMapping("/todos/{id}")
  public Object updateTodo(@RequestBody Todo todo, @PathVariable("id") int id) {
    for(int i = 0; i < list.size(); i++) {
      Todo t = list.get(i);
      if(t.getId() == id){
        todo.setId((long) id);
        list.set(i,todo);
        return todo;
      }
    }
    return "\"message\":\"" + String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id);
  }

  @DeleteMapping("/todos/{id}")
  public Object deleteTodo(@PathVariable("id") int id){
    for(int i = 0; i < list.size(); i++) {
      Todo t = list.get(i);
      if(t.getId() == id){
        list.remove(t);
        return list;
      }
    }
    return "\"message\":\"" + String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id);
  }
}
