const getCompany = async () => {
    try {
      let sl =document.getElementById("choose").value;
      let st = document.getElementById("pagination").value;
      payload = { pageNo: st, pageSize: sl };
      let val = document.getElementById("choose").value;
      let code = document.getElementById("code").value;
      let name = document.getElementById("name").value;
      let email = document.getElementById("email").value;
      let phone = document.getElementById("phone_number").value;
      let status = document.getElementById("form_select").value;

  if (payload == null || payload == "" || payload == undefined) {
      payload = { pageSize: cc, pageNo: 1 };
  }
  
  if (code !== "") {
      Object.assign(payload, { textCode: code });

  }
  if (name !== "") {
      Object.assign(payload, { textName: name });
  }
  if (email !== "") {
      Object.assign(payload, { textEmail: email });
  }
  if (phone !== "") {
      Object.assign(payload, { textPhone: phone });
  }
  if (status !== "") {
      Object.assign(payload, { textStatus: status });
  }
      const response = await axios.post('http://localhost:8080/api/company/SearchCompanies',payload);
  
      const company = response.data.content;
      console.log(company);

        var tmp = "";
            for(let i = 0; i < company.length; i++){
                tmp +='<tr>';
                tmp    +=    '<td>'+company[i].code+'</td>';
                tmp        +=    '<td>'+company[i].name+'</td>';
                tmp        +=    '<td>'+company[i].email+'</td>';
                tmp        +=    '<td>'+company[i].phoneNumber+'</td>';
                tmp        +=    '<td id="'+company[i].status+'">'+company[i].status+'</td>';
                tmp        +=    '<td><button style="background: #30C0F0 0% 0% no-repeat padding-box;" onclick="detail('+company[i].id+')">Detail</button><button style="background: #D31212 0% 0% no-repeat padding-box;">Delete</button></td>';
                tmp        +='</tr>';
                
            }
                document.getElementById("table_body").innerHTML = tmp;
                updatePage(company.length)
            return company;
    } catch (errors) {
      console.error(errors);
    }
  };
// getCompany();
document.getElementById("Search").onclick = function(){getCompany()};
document.getElementById("choose").addEventListener('change',async function(){
  await getCompany();
})
const getPage = (abc) => {
  
}

document.getElementById("register").addEventListener("click", async function (e) {
  window.location.href = "http://127.0.0.1:5500/html/CompanyRegister.html";
});

document.getElementById("back").addEventListener("click", async function (e) {
  window.location.reload();
});
const detail = (id) => {
  localStorage.setItem('id', id);
  window.location.href = "http://127.0.0.1:5500/html/CompanyDetail.html";
}

// pagination element
const createPageElement = (id) => {
  var li = document.createElement('li');
  li.setAttribute('class', 'page-item');

  var a = document.createElement('a');
  a.innerText = id;
  a.setAttribute('class', 'page-link text-primary');
  a.setAttribute('id', 'link-' + id);
  a.setAttribute('onclick', 'getPage(' + id + ')');

  li.appendChild(a);

  return li;
}


const updatePage = totalPages => {
  const pageList = document.querySelector('ul');

  var next = document.createElement('a');
  next.innerText = 'Next';
  next.setAttribute('class', 'page-link text-primary');
  next.setAttribute('id', 'link-next');
  next.setAttribute('onclick', 'nextPPage()');

  var pre = document.createElement('a');
  pre.innerText = 'Previous';
  pre.setAttribute('class', 'page-link text-primary');
  pre.setAttribute('id', 'link-pre');
  pre.setAttribute('onclick', 'prePage()');

  pageList.appendChild(pre);

  // if (totalPages < 5) {
      for (let index = 1; index <= totalPages; index++) {
          pageList.appendChild(createPageElement(index));
       }
  // } else {
  //     let pageNextTo = Number(localStorage.getItem('pageNextTo'));
  //     var dot = document.createElement('a');
  //     dot.innerText = '...';
  //     dot.setAttribute('class', 'page-link text-primary');
  //     //dot.setAttribute('onclick', 'dotClick('+pageNextTo+')');
  //     //dot.setAttribute('onclick', 'dotPage(' + pages + ')');

  //     for (let index = 1; index <= 3; index++) {
  //         pageList.appendChild(createPageElement(index));
  //     }
      // pageList.appendChild(dot);
      // pageList.appendChild(createPageElement(totalPages));

  // }
  pageList.appendChild(next);
  localStorage.setItem('totalPages', totalPages);
};