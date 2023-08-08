window.addEventListener('load', () => {
    todos = JSON.parse(localStorage.getItem('todos')) || [];
    const nameInput = document.querySelector('#name');
    const newTodoForm = document.querySelector('#new-todo-form');
    getUsername(nameInput);

    nameInput.addEventListener('change', e => {
        setUsername(e.target.value);
        //localStorage.setItem('username', e.target.value);
    })

    newTodoForm.addEventListener('submit', e=> {
        e.preventDefault();

        if (e.target.elements.content.value != "") {

            let categoryValue = e.target.elements.category.value;

            if (categoryValue == "") {
                categoryValue="business"
            }

        const todo = {
            content: e.target.elements.content.value,
            category: categoryValue,
            done: false,
            createdAt: new Date().getTime()
        }

        todos.push(todo);

        localStorage.setItem('todos', JSON.stringify(todos));

        e.target.reset();

        DisplayTodos();
    }
    })

    DisplayTodos();
});

function DisplayTodos() {
    const todoList = document.querySelector('#todo-list');

    todoList.innerHTML = '';

    todos.forEach(todo => {
        const todoItem = document.createElement('div');
        todoItem.classList.add('todo-item');

        const span = document.createElement('span');
        const content = document.createElement('div');
        const actions = document.createElement('div');
        const edit = document.createElement('button');
        const deleteButton = document.createElement('button')
        const upButton = document.createElement('button')

        span.classList.add('bubble'); 

        if (todo.category == 'personal') {
            span.classList.add('personal');
        } else {
            span.classList.add('business');
        }

        content.classList.add('todo-content');
        actions.classList.add('actions');
        upButton.classList.add('up')
        deleteButton.classList.add('delete');

        content.innerHTML = 
        `
        <textarea readonly>${todo.content}</textarea>
        `;
        
        deleteButton.innerHTML = 'Delete';
        upButton.innerHTML = 'UP'

        actions.appendChild(upButton);
        actions.appendChild(deleteButton);
        todoItem.appendChild(content);
        todoItem.appendChild(actions);

        todoList.appendChild(todoItem);

        if (todo.done) {
            todoItem.classList.add('done');
        }

        deleteButton.addEventListener('click', e => {
            todos = todos.filter(t => t != todo);
            localStorage.setItem('todos', JSON.stringify(todos));
            DisplayTodos();
        })
    })
}

async function getUsername(nameInput) {
    const url = `http://localhost:122/person/1/username`;
    const response = await fetch(url);
    const answer = await response.text();
    nameInput.value = answer; 
}

async function setUsername(name) {
    let url = `http://localhost:122/person/1/username?newUsername=${name}`;
    await fetch(url, {
        method: 'PATCH',
        mode: 'cors' 
    });
}