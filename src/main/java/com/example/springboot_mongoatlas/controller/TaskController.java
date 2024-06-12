package com.example.springboot_mongoatlas.controller;

import com.example.springboot_mongoatlas.entity.Task;
import com.example.springboot_mongoatlas.kafka.JsonKafkaProducerService;
import com.example.springboot_mongoatlas.kafka.KafkaProducerService;
import com.example.springboot_mongoatlas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private JsonKafkaProducerService jsonKafkaProducerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @GetMapping("/getAllTasks")
    public List<Task> findAllTasks(){
        return taskService.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task findTaskByTaskId(@PathVariable String taskId){
        return taskService.findTaskByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}")
    public List<Task> getTaskBySeverity(@PathVariable int severity){
        return taskService.findTaskBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee){
        return taskService.getTaskByAssignee(assignee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task updateTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTask(@RequestBody String taskId){
        return taskService.deleteTask(taskId);
    }

    @PostMapping("/publish/{message}")
    @ResponseStatus(HttpStatus.CREATED)
    public String publish(@PathVariable String message){
        kafkaProducerService.sendMessage(message);
        return "Message sent to the topic";
    }

    @PostMapping("/publish/JsonMessage")
    @ResponseStatus(HttpStatus.CREATED)
    public String publish(@RequestBody Task task){
        jsonKafkaProducerService.sendMessage(task);
        return "Json Message sent to kafka topic";
    }
}
