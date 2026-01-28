package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // Shared CSS and Layout
    private String getHtmlPage(String title, String content) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>%s - Todo App</title>
                    <style>
                        :root {
                            --primary: #667eea;
                            --secondary: #764ba2;
                            --success: #10b981;
                            --danger: #ef4444;
                            --warning: #f59e0b;
                            --bg: #f3f4f6;
                            --card-bg: #ffffff;
                            --text: #1f2937;
                        }
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, var(--primary) 0%%, var(--secondary) 100%%);
                            min-height: 100vh;
                            padding: 20px;
                            color: var(--text);
                        }
                        .container {
                            max-width: 900px;
                            margin: 0 auto;
                            background: var(--card-bg);
                            border-radius: 15px;
                            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
                            padding: 30px;
                            animation: slideIn 0.4s ease-out;
                        }
                        @keyframes slideIn {
                            from { opacity: 0; transform: translateY(20px); }
                            to { opacity: 1; transform: translateY(0); }
                        }
                        h1 { color: var(--primary); margin-bottom: 20px; font-size: 2em; }
                        .btn {
                            display: inline-block;
                            padding: 10px 20px;
                            border-radius: 8px;
                            text-decoration: none;
                            color: white;
                            font-weight: 600;
                            border: none;
                            cursor: pointer;
                            transition: transform 0.2s, box-shadow 0.2s;
                        }
                        .btn:hover { transform: translateY(-2px); box-shadow: 0 4px 12px rgba(0,0,0,0.15); }
                        .btn-primary { background: var(--primary); }
                        .btn-success { background: var(--success); }
                        .btn-danger { background: var(--danger); }
                        .btn-warning { background: var(--warning); }
                        .btn-outline { background: transparent; border: 2px solid var(--primary); color: var(--primary); }
                        .btn-outline:hover { background: var(--primary); color: white; }

                        .nav { margin-bottom: 30px; border-bottom: 2px solid #eee; padding-bottom: 15px; }
                        .nav a { margin-right: 15px; color: #666; text-decoration: none; font-weight: 500; }
                        .nav a:hover { color: var(--primary); }
                        .nav a.active { color: var(--primary); font-weight: bold; }

                        table { width: 100%%; border-collapse: collapse; margin-top: 20px; }
                        th, td { padding: 15px; text-align: left; border-bottom: 1px solid #eee; }
                        th { color: #888; font-weight: 600; text-transform: uppercase; font-size: 0.85em; }
                        tr:hover { background: #f9fafb; }

                        .badge {
                            padding: 4px 8px;
                            border-radius: 4px;
                            font-size: 0.85em;
                            font-weight: 600;
                        }
                        .badge-done { background: #d1fae5; color: #065f46; }
                        .badge-todo { background: #fee2e2; color: #991b1b; }

                        .form-group { margin-bottom: 20px; }
                        label { display: block; margin-bottom: 8px; font-weight: 600; color: #4b5563; }
                        input[type="text"] {
                            width: 100%%;
                            padding: 12px;
                            border: 2px solid #e5e7eb;
                            border-radius: 8px;
                            font-size: 1em;
                            transition: border-color 0.2s;
                        }
                        input[type="text"]:focus { border-color: var(--primary); outline: none; }

                        .actions { display: flex; gap: 10px; }

                        /* Toast notification for JS */
                        .toast {
                             position: fixed; bottom: 20px; right: 20px;
                             background: #333; color: white; padding: 12px 24px;
                             border-radius: 8px; opacity: 0; transition: opacity 0.3s;
                        }
                        .toast.show { opacity: 1; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="nav">
                            <a href="/">Home</a>
                            <a href="/todos">My Todos</a>
                        </div>
                        <h1>%s</h1>
                        %s
                    </div>
                    <div id="toast" class="toast">Action Successful!</div>
                    <script>
                        function showToast(msg) {
                            const t = document.getElementById('toast');
                            t.textContent = msg || 'Success!';
                            t.classList.add('show');
                            setTimeout(() => t.classList.remove('show'), 3000);
                        }

                        // API Helpers
                        async function deleteTodo(id) {
                            if(!confirm('Are you sure?')) return;
                            try {
                                const res = await fetch('/api/todos/' + id, { method: 'DELETE' });
                                if(res.ok) {
                                    window.location.reload();
                                }
                            } catch(e) { alert('Error deleting'); }
                        }
                    </script>
                </body>
                </html>
                """
                .formatted(title, title, content);
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return getHtmlPage("🚀 Welcome to Todo App",
                """
                        <div style="text-align: center; padding: 40px 0;">
                            <p style="font-size: 1.2em; color: #666; margin-bottom: 30px;">
                                Your personal task manager built with Spring Boot.
                            </p>
                            <div style="display: flex; gap: 20px; justify-content: center;">
                                <a href="/todos" class="btn btn-primary" style="font-size: 1.2em; padding: 15px 30px;">
                                    Manage My Todos
                                </a>
                                <a href="/todos/new" class="btn btn-outline" style="font-size: 1.2em; padding: 15px 30px;">
                                    Create New Task
                                </a>
                            </div>

                        </div>
                        """);
    }

    @GetMapping("/todos")
    @ResponseBody
    public String listTodos() {
        return getHtmlPage("📋 My Todo List",
                """
                        <div style="display: flex; justify-content: space-between; margin-bottom: 20px;">
                            <a href="/todos/new" class="btn btn-success">+ New Task</a>
                            <button onclick="window.location.reload()" class="btn btn-outline">Refresh</button>
                        </div>

                        <div id="todo-list">Loading todos...</div>

                        <script>
                            fetch('/api/todos')
                                .then(res => res.json())
                                .then(todos => {
                                    const list = document.getElementById('todo-list');
                                    if(todos.length === 0) {
                                        list.innerHTML = '<p style="text-align: center; color: #888;">No tasks yet. Create one!</p>';
                                        return;
                                    }

                                    let html = '<table><thead><tr><th>Task</th><th>Status</th><th>Actions</th></tr></thead><tbody>';
                                    todos.forEach(todo => {
                                        const statusBadge = todo.completed ?
                                            '<span class="badge badge-done">Completed</span>' :
                                            '<span class="badge badge-todo">Pending</span>';

                                        html += `
                                            <tr>
                                                <td>
                                                    <a href="/todos/${todo.id}" style="font-weight: bold; color: #333; text-decoration: none;">
                                                        ${todo.text}
                                                    </a>
                                                </td>
                                                <td>${statusBadge}</td>
                                                <td class="actions">
                                                    <a href="/todos/${todo.id}" class="btn btn-outline" style="padding: 5px 10px; font-size: 0.8em;">View</a>
                                                    <a href="/todos/${todo.id}/edit" class="btn btn-warning" style="padding: 5px 10px; font-size: 0.8em; color: white;">Edit</a>
                                                    <button onclick="deleteTodo(${todo.id})" class="btn btn-danger" style="padding: 5px 10px; font-size: 0.8em;">Delete</button>
                                                </td>
                                            </tr>
                                        `;
                                    });
                                    html += '</tbody></table>';
                                    list.innerHTML = html;
                                });
                        </script>
                        """);
    }

    @GetMapping("/todos/new")
    @ResponseBody
    public String createTodoForm() {
        return getHtmlPage("➕ Create New Task", """
                <form id="createForm" style="max-width: 600px;">
                    <div class="form-group">
                        <label>Task Description</label>
                        <input type="text" id="text" required placeholder="What needs to be done?">
                    </div>

                    <div class="form-group">
                        <label>
                            <input type="checkbox" id="completed"> Mark as completed
                        </label>
                    </div>

                    <div style="margin-top: 30px;">
                        <button type="submit" class="btn btn-success">Create Task</button>
                        <a href="/todos" class="btn btn-outline" style="margin-left: 10px;">Cancel</a>
                    </div>
                </form>

                <script>
                    document.getElementById('createForm').onsubmit = async (e) => {
                        e.preventDefault();
                        const text = document.getElementById('text').value;
                        const completed = document.getElementById('completed').checked;

                        try {
                            const res = await fetch('/api/todos', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify({ text, completed })
                            });

                            if(res.ok) {
                                window.location.href = '/todos';
                            }
                        } catch(err) { alert('Error creating task'); }
                    };
                </script>
                """);
    }

    @GetMapping("/todos/{id}/edit")
    @ResponseBody
    public String editTodoForm(@PathVariable Long id) {
        return getHtmlPage("✏️ Edit Task", """
                <form id="editForm" style="max-width: 600px;">
                    <input type="hidden" id="todoId" value="%d">
                    <div class="form-group">
                        <label>Task Description</label>
                        <input type="text" id="text" required>
                    </div>

                    <div class="form-group">
                        <label>
                            <input type="checkbox" id="completed"> Mark as completed
                        </label>
                    </div>

                    <div style="margin-top: 30px;">
                        <button type="submit" class="btn btn-primary">Update Task</button>
                        <a href="/todos" class="btn btn-outline" style="margin-left: 10px;">Cancel</a>
                    </div>
                </form>

                <script>
                    const id = document.getElementById('todoId').value;

                    // Load data
                    fetch('/api/todos/' + id)
                        .then(res => res.json())
                        .then(todo => {
                            document.getElementById('text').value = todo.text;
                            document.getElementById('completed').checked = todo.completed;
                        });

                    document.getElementById('editForm').onsubmit = async (e) => {
                        e.preventDefault();
                        const text = document.getElementById('text').value;
                        const completed = document.getElementById('completed').checked;

                        try {
                            const res = await fetch('/api/todos/' + id, {
                                method: 'PUT',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify({ text, completed })
                            });

                            if(res.ok) {
                                window.location.href = '/todos';
                            }
                        } catch(err) { alert('Error updating task'); }
                    };
                </script>
                """.formatted(id));
    }

    @GetMapping("/todos/{id}")
    @ResponseBody
    public String viewTodo(@PathVariable Long id) {
        return getHtmlPage("ℹ️ Task Details", """
                <div id="loading">Loading...</div>
                <div id="details" style="display:none;">
                    <div style="background: #f8fafc; padding: 20px; border-radius: 8px; margin-bottom: 30px;">
                        <h2 id="view-text" style="color: #333; margin-bottom: 10px;"></h2>
                        <div id="view-status"></div>
                        <p style="color: #888; margin-top: 10px; font-size: 0.9em;">ID: <span id="view-id"></span></p>
                    </div>

                    <div class="actions">
                        <a href="/todos" class="btn btn-outline">Back to List</a>
                        <a id="btn-edit" href="#" class="btn btn-warning" style="color:white">Edit</a>
                        <button id="btn-delete" class="btn btn-danger">Delete</button>
                    </div>
                </div>

                <script>
                    const id = %d;
                    fetch('/api/todos/' + id)
                        .then(res => {
                            if(!res.ok) {
                                document.getElementById('loading').innerHTML = 'Task not found';
                                return;
                            }
                            return res.json();
                        })
                        .then(todo => {
                            if(!todo) return;

                            document.getElementById('loading').style.display = 'none';
                            document.getElementById('details').style.display = 'block';

                            document.getElementById('view-text').textContent = todo.text;
                            document.getElementById('view-id').textContent = todo.id;

                            const badge = todo.completed ?
                                '<span class="badge badge-done">Completed</span>' :
                                '<span class="badge badge-todo">Pending</span>';
                            document.getElementById('view-status').innerHTML = badge;

                            document.getElementById('btn-edit').href = '/todos/' + id + '/edit';
                            document.getElementById('btn-delete').onclick = () => deleteTodo(id);
                        });
                </script>
                """.formatted(id));
    }
}
