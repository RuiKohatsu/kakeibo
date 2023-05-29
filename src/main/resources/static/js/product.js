'use strict';


function tableCreate(data) {
  var table = document.createElement('table');
  // table.id = productTable;
  // ヘッダーを作成
  var tr = document.createElement('tr');
  //ラジオボタンのセル
  var th = document.createElement('th');
  tr.appendChild(th);

  for (let key in data[0]) {
          // th要素を生成
          var th = document.createElement('th');
          // th要素内にテキストを追加
          th.textContent = key;
          // th要素をtr要素の子要素に追加
          tr.appendChild(th);
          }
      // tr要素をtable要素の子要素に追加
      table.appendChild(tr);

  // テーブル本体を作成
  for (var i = 0; i < data.length; i++) {
      var td = document.createElement('td');
      var radio = document.createElement('input');
      radio.type = 'radio';
      radio.name = 'product';
      radio.className = 'radio-btn';
      radio.id = i;
      td.appendChild(radio);
      // tr要素を生成
      var tr = document.createElement('tr');
      tr.appendChild(td);
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
  document.getElementById('maintable').appendChild(table);
}

  function deleteProduct(rowId){
    document.getElementById('deleteBtn').addEventListener('click', () => {
      
      fetch(`/api/product/${rowId}`,{
        method: "DELETE",
        // headers: {
        //   'Content-Type': 'application/json'
        // },
        // body: JSON.stringify(rowId)
      })
        .then(res => {
          if(res.status === 400) {
            document.getElementById('message').textContent = "データが見つかりませんでした";
          } else {
            res.json()
            .then(data => {
              document.getElementById('message').textContent = '1件のデータを削除しました';
              fetch(`/api/products`)
                .then(res => {
                  if(res.status === 400) {
                    document.getElementById('message').textContent = "データが見つかりませんでした";
                  }else {

                    res.json()
                    .then(data => {
                      document.getElementById('maintable').textContent = '';
                      tableCreate(data);
                      radioCheck();
                    });
                  }
              });
            })
          }
        });
    });
  }

  function updateProduct(rowId){   
    document.getElementById('updateBtn').addEventListener('click', () => {
      const nameUpdate = document.getElementById('updateNameInput').value;
      const priceUpdate = document.getElementById('updatePriceInput').value;
      console.log(nameUpdate);
      console.log(priceUpdate);
      const array = [nameUpdate, priceUpdate];
      fetch(`/api/product/${rowId}`,{
        method: "PUT",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(array)
      })
        .then(res => {
          if(res.status === 400) {
            document.getElementById('message').textContent = "データが見つかりませんでした";
          } else {
            res.json()
            .then(data => {
              document.getElementById('message').textContent = '1件のデータを更新しました';
              fetch(`/api/products`)
                .then(res => {
                  if(res.status === 400) {
                    document.getElementById('message').textContent = "データが見つかりませんでした";
                  }else {

                    res.json()
                    .then(data => {
                      document.getElementById('maintable').textContent = '';
                      tableCreate(data);
                      radioCheck();
                    });
                  }
              });
            })
          }
        });
    });
  }

  function radioCheck(){
    const radioButtons = document.getElementsByClassName("radio-btn");
      // イベントリスナーを設定
      Array.from(radioButtons).forEach(radioButton => {
        radioButton.addEventListener("change", function () {
          if (this.checked) {
            // ラジオボタンが選択された場合の処理
            const rowId = this.parentElement.nextElementSibling.textContent;
            const rowName = this.parentElement.nextElementSibling.nextElementSibling.textContent;
            const rowPrice = this.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.textContent;

            console.log("選択されたid:", rowId);
            console.log("選択された商品名:", rowName);
            console.log("選択された値段:", rowPrice);

            const productDetail = document.getElementById('detail');
            productDetail.textContent = '';
            const h2_detail = document.createElement('h2');
            h2_detail.textContent = '商品詳細';
            h2_detail.appendChild(document.createElement('br'));
            productDetail.appendChild(h2_detail);

            const productId = document.createElement('span');
            productId.textContent = `id: ${rowId}` ;
            productId.appendChild(document.createElement('br'));
            productDetail.appendChild(productId);

            const productName = document.createElement('span');
            productName.textContent = `商品名：${rowName}`;
            
            productDetail.appendChild(productName);

            const nameInput = document.createElement('input');
            nameInput.id = 'updateNameInput';
            productDetail.appendChild(nameInput);
            productDetail.appendChild(document.createElement('br'));

            const productPrice = document.createElement('span');
            productPrice.textContent = `値段：${rowPrice}`;
            productDetail.appendChild(productPrice);

            const priceInput = document.createElement('input');
            priceInput.id = 'updatePriceInput';
            productDetail.appendChild(priceInput);
            productDetail.appendChild(document.createElement('br'));

            const deleteBtn = document.createElement('button');
            deleteBtn.id = 'deleteBtn';
            deleteBtn.textContent = '削除';

            const updateBtn = document.createElement('button');
            updateBtn.id = 'updateBtn';
            updateBtn.textContent = '更新';

            productDetail.appendChild(deleteBtn);
            productDetail.appendChild(updateBtn);
            deleteProduct(rowId);

            updateProduct(rowId);
            


            
            
          }
        });
      });
  }

function changeElement(el){
        if(el.style.display==''){
          el.style.display='none';
        }else{
          el.style.display='';
        }
      }



    fetch(`/api/products`)
      .then(res => {
        if(res.status === 400) {
          document.getElementById('message').textContent = "データが見つかりませんでした";
        }else {
          res.json()
          .then(data => {
            tableCreate(data);
            radioCheck();

            
          });
        }
      });
      
      // const div_detail = document.getElementById('detail');
      // div_detail.style.display='none';


      // const h1_detail = document.createElement('h1');
      // h1_detail.textContent = '商品詳細';
      // div_detail.appendChild(h1_detail);
   
      const div1 = document.getElementById('newAdd-text');
      const h2_add = document.createElement('h2');
      h2_add.textContent = '商品追加';
      div1.appendChild(h2_add);

      
      const div2 = document.getElementById('name');
      const productName = document.createElement('span');
      productName.textContent = '商品名：';
      div2.appendChild(productName);

      const nameInput = document.createElement('input');
      nameInput.id = 'nameInput';
      productName.appendChild(nameInput);

      const div3 = document.getElementById('price');
      const productPrice = document.createElement('span');
      productPrice.textContent = '値段：';
      div3.appendChild(productPrice);

      const priceInput = document.createElement('input');
      priceInput.id = 'priceInput';
      productPrice.appendChild(priceInput);

      const div4 = document.getElementById('add-Btn');
      const addBtn = document.createElement('button');
      addBtn.id = 'addBtn';
      addBtn.textContent = '追加';
      div4.appendChild(addBtn);
      
      //追加ボタンを押したとき
      document.getElementById('addBtn').addEventListener('click', () => {
      const productName = document.getElementById('nameInput').value
      const productPrice = document.getElementById('priceInput').value

      //textbox値の確認
      console.log(document.getElementById('nameInput').value);

      const inputData = {
        name: productName,
        price: productPrice
      };

      fetch(`/api/product`,{
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputData)
      })
        .then(res => {
          if(res.status === 400) {
            document.getElementById('message').textContent = 'データを追加できませんでした';
          } else {
            res.json()
            .then(data => {
              document.getElementById('message').textContent = '1件のデータを追加しました';
              fetch(`/api/products`)
                .then(res => {
                  if(res.status === 400) {
                    document.getElementById('message').textContent = "データが見つかりませんでした";
                  }else {

                    res.json()
                    .then(data => {
                      document.getElementById('maintable').textContent = '';
                      tableCreate(data);
                      radioCheck();
                    });
                  }
              });
            })
          }
      });
    });

  

    
 


