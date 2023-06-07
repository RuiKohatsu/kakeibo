function highlightCells(tableId, searchString) {
    const table = document.querySelector('Table');
    const cells = table.getElementsByTagName('td');

    for (var i = 0; i < cells.length; i++) {
      let cell = cells[i];

      if (cell.innerHTML.includes(searchString)) {
        cell.classList.add('highlight');
      }
    }
  }
  

  function tableCreate(data) {
    const table = document.getElementById('Table');
    var rows = document.getElementsByClassName('rows');
    // rows.textContent = '';
    var rows = document.getElementsByClassName('rows');
    while (rows.length > 0) {
        rows[0].parentNode.removeChild(rows[0]);
    }

    // テーブル本体を作成
    for (var i = 0; i < data.length; i++) {
        // tr要素を生成
        var tr = document.createElement('tr');
        tr.className = 'rows';
        // th・td部分のループ
        for (let key in data[0]) {
            // td要素を生成
            var td = document.createElement('td');
            // td要素内にテキストを追加
            td.textContent = data[i][key];
            // td要素をtr要素の子要素に追加
            tr.appendChild(td);
        }
        // tr要素をtable要素の子要素に追加
        table.appendChild(tr);
    }
    // 生成したtable要素を追加する
    // document.getElementById('maintable').appendChild(table);
    highlightCells('Table', '-');
  }
  


function check(){
    const check_btn = document.getElementById("check_btn");
    // イベントリスナーを設定

    check_btn.addEventListener("click", function () {
      var flag = false; // 選択されているか否かを判定する変数
      const categories = [];
      for (var i = 0; i < document.category_form.category.length; i++) {
 
        // i番目のチェックボックスがチェックされているかを判定
          if (document.category_form.category[i].checked) {
            flag = true;
            categories.push(document.category_form.category[i].value);    
            
          }
          
      }
      
 
      // 何も選択されていない場合の処理   
      if (!flag) {
        alert("項目が選択されていません。");
      }
      else if(flag){
        console.log(categories);
        fetch(`/api/reflect`,{
          method: "POST",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(categories)
        })
          .then(res => {
            if(res.status === 400) {
              document.getElementById('message').textContent = 'データを反映できませんでした';
            } else {
              res.json()
              .then(data => {
                document.getElementById('message').textContent = '';     
                tableCreate(data);          
              });
            }
        });
      }
    });

}


const check1 = document.getElementById("check1");
const check2 = document.getElementById("check2");
const check3 = document.getElementById("check3");
const check4 = document.getElementById("check4");
const check5 = document.getElementById("check5");
const check6 = document.getElementById("check6");
const check7 = document.getElementById("check7");
const check8 = document.getElementById("check8");
const check9 = document.getElementById("check9");
const check10 = document.getElementById("check10");

// チェックボックス1がクリックされたときの処理
check1.addEventListener("click", function() {
  if (check1.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check2.checked = false;
    check3.checked = false;
    check4.checked = false;
    check5.checked = false;
    check6.checked = false;
    check7.checked = false;
    check8.checked = false;
    check9.checked = false;
    check10.checked = false;
  }
});
check2.addEventListener("click", function() {
  if (check2.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check3.addEventListener("click", function() {
  if (check3.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check4.addEventListener("click", function() {
  if (check4.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check5.addEventListener("click", function() {
  if (check5.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check6.addEventListener("click", function() {
  if (check6.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check7.addEventListener("click", function() {
  if (check7.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check8.addEventListener("click", function() {
  if (check8.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check9.addEventListener("click", function() {
  if (check9.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

check10.addEventListener("click", function() {
  if (check10.checked) {
    // チェックボックス1がチェックされた場合、他のチェックボックスのチェックを外す
    check1.checked = false;
  }
});

window.onload = function() {
  highlightCells('Table', '-');
  };

check();





  