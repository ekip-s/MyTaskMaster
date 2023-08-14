window.addEventListener('load', async () => {
    todos = JSON.parse(localStorage.getItem('todos')) || [];
    const nameInput = document.querySelector('#name');
    const newTodoForm = document.querySelector('#new-todo-form');
    getUsername(nameInput);

    nameInput.addEventListener('change', async e => {
        await setUsername(e.target.value);
    })

    newTodoForm.addEventListener('submit', async e=> {
        e.preventDefault();

        if (e.target.elements.content.value != "") {

            let categoryValue = e.target.elements.category.value;

            if (categoryValue == "") {
                categoryValue="business"
            }

        const todo = {
            content: e.target.elements.content.value,
            category: categoryValue,
            isDone: false, 
            importanceLevel: 0
        }

        await postTask(todo);

        e.target.reset();

        await getTasks()
    }
    })

    await getTasks()
});

async function getUsername(nameInput) {
    const url = `http://localhost:122/person/1/username`;
    const response = await fetch(url);
    const answer = await response.text();
    nameInput.value = answer; 
};

async function getTasks() {
    const url = `http://localhost:122/task/1`;
try {
    const answer = await (await fetch(url)).json();

    const todoList = document.querySelector('#todo-list');
    todoList.innerHTML = '';

    answer.forEach(todo => {
        const todoItem = document.createElement('div');
        todoItem.classList.add('todo-item');
        todoItem.id = todo.id;

        const span = document.createElement('span');
        const content = document.createElement('div');
        const actions = document.createElement('div');
        const edit = document.createElement('button');
        const deleteButton = document.createElement('button');
        const upButton = document.createElement('button');
        const lowButton = document.createElement('button');
        const doneButton = document.createElement('button');

        span.classList.add('bubble'); 

        if (todo.category == 'personal') {
            span.classList.add('personal');
        } else {
            span.classList.add('business');
        }

        content.classList.add('todo-content');
        actions.classList.add('actions');
        upButton.classList.add('up');
        lowButton.classList.add('low');
        doneButton.classList.add('done');
        deleteButton.classList.add('delete');

        content.innerHTML = 
        `
        <textarea readonly>${todo.content}</textarea>
        `;
        
        deleteButton.innerHTML = 'Delete';
        upButton.innerHTML = 'UP &#9650;';
        lowButton.innerHTML = 'LOW &#9660;';
        doneButton.innerHTML = 'Done';

        actions.appendChild(doneButton);
        actions.appendChild(lowButton);
        actions.appendChild(upButton);
        actions.appendChild(deleteButton);
        todoItem.appendChild(content);
        todoItem.appendChild(actions);

        todoList.appendChild(todoItem);

        if (todo.done) {
            todoItem.classList.add('done');
        }

        deleteButton.addEventListener('click', async e => {
            let deleteUrl = `http://localhost:122/task/1/delete/${e.target.closest('.todo-item').id}`;
            await fetch(deleteUrl, {
                method: 'DELETE',
                mode: 'cors' 
                });
            getTasks()
        });

        upButton.addEventListener('click', async e => {
            let upUrl = `http://localhost:122/task/1/up/${e.target.closest('.todo-item').id}`;
            await fetch(upUrl, {
                method: 'PATCH',
                mode: 'cors' 
                });
            getTasks()
        });

        lowButton.addEventListener('click', async e => {
            let upUrl = `http://localhost:122/task/1/low/${e.target.closest('.todo-item').id}`;
            await fetch(upUrl, {
                method: 'PATCH',
                mode: 'cors' 
                });
            getTasks()
        });

        doneButton.addEventListener('click', async e => {
            let upUrl = `http://localhost:122/task/1/done/${e.target.closest('.todo-item').id}`;
            await fetch(upUrl, {
                method: 'PATCH',
                mode: 'cors' 
                });
            getTasks()
        });
    })} catch (error) {
        console.log(error);
    }
}

async function setUsername(name) {
    let url = `http://localhost:122/person/1/username?newUsername=${name}`;
    try {
        await fetch(url, {
            method: 'PATCH',
            mode: 'cors' 
            });
        } catch(err) {
            console.error(err);
        }
}



async function postTask(task) {
    let url = `http://localhost:122/task/1`;
    try {
        await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
                },
                mode: 'cors',
                body: JSON.stringify(task)
                });
            } catch(err) {
                console.error(err)
            }
}