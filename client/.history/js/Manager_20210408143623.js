const getCompany = async () => {
    try {
      let cc =document.getElementById("choose").value;

      payload = { pageNo: 0, pageSize: cc };
      let val = document.getElementById("choose").value;
      let code = document.getElementById("code").value;
      let name = document.getElementById("name").value;
      let email = document.getElementById("email").value;
      let phone = document.getElementById("phone_number").value;
      let status = document.getElementById("form_select").value;

  if (payload == null || payload == "" || payload == undefined) {
      payload = { pageSize: val, pageIndex: 1 };
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
                tmp        +=    '<td><button style="background: #30C0F0 0% 0% no-repeat padding-box;" id="detail'+company[i].id+'">Detail</button><button style="background: #D31212 0% 0% no-repeat padding-box;">Delete</button></td>';
                tmp        +='</tr>';
                
            }
                document.getElementById("table_body").innerHTML = tmp;
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

document.getElementById("register").addEventListener("click", async function (e) {
  window.location.href = "http://127.0.0.1:5500/html/CompanyRegister.html";
});

document.getElementById("back").addEventListener("click", async function (e) {
  window.location.reload();
});
const getDetail = (id) => {
  localStorage.setItem('id', id);
  window.location.href = "http://127.0.0.1:5500//layout/detail.html";
}
document.getElementById("detail").addEventListener("click", async function (e) {
  
});