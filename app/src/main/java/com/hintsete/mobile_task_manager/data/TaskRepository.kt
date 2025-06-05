package com.hintsete.mobile_task_manager.data

class TaskRepository{
    private val tasks= mutableListOf<Task>()
    private  var nextId=1

    fun getTasks():List<Task>{
        return tasks.toList()
    }

    fun addTasks(title:String):Task{
        val task=Task(nextId++,title)
        tasks.add(task)
        return  task
    }

    fun updateTask(task:Task){
        val index= tasks.indexOfFirst { it.id==task.id }
        if(index!=-1){
            tasks[index]=task
        }
    }

    fun deleteTask(taskId:Int){
        tasks.removeIf { it.id==taskId }
    }
}