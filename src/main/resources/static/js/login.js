function openModal() {
    let gray_out = document.getElementById("fadeLayer");
    gray_out.style.visibility = "visible";
    setTimeout(addClass, 200);
}

function closeModal() {
    let modal = document.getElementById('modal');
    let gray_out = document.getElementById("fadeLayer");
    modal.classList.remove('is-show');
    gray_out.style.visibility ="hidden";
}

function addClass() {
    let modal = document.getElementById('modal');
    modal.classList.add('is-show');
}

'use strict'

document.getElementById('login-btn').addEventListener('click', () => {
    const userId = document.getElementById('userId').value
    const userPass = document.getElementById('userPass').value
    console.log(userId);
    console.log(userPass);

    const inputData = [userId, userPass];



    fetch(`/api/login`,{
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputData)
    })
    .then(res => {
        if(res.status === 400) {
          document.getElementById('client').textContent = 'IDまたはパスワードが不正です';
        } else {
          res.json()
          .then(data => {
            if(data == 0){
                document.getElementById('idError').textContent = 'IDは必須です';
                document.getElementById('passError').textContent = '';
                document.getElementById('client').textContent = '';
            }else if(data == 1){
                document.getElementById('idError').textContent = '';
                document.getElementById('passError').textContent = 'PASSは必須です';
                document.getElementById('client').textContent = '';
            }else if(data == 2){
                document.getElementById('idError').textContent = 'IDは必須です';
                document.getElementById('passError').textContent = 'PASSは必須です';
                document.getElementById('client').textContent = '';
            }else if(data == 3){
                document.getElementById('idError').textContent = '';
                document.getElementById('passError').textContent = '';
                document.getElementById('client').textContent = 'IDまたはパスワードが不正です';
            }else if(data == 4){
                location.href = '/menu'
            }
          })
        }
    });

})