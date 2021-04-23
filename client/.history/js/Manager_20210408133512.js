const getCompany = async () => {
    try {
      let cc =document.getElementById("choose").value;

      payload = { pageNo: 0, pageSize: cc };
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
                tmp        +=    '<td><button style="background: #30C0F0 0% 0% no-repeat padding-box;">Detail</button><button style="background: #D31212 0% 0% no-repeat padding-box;">Delete</button></td>';
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


const searchAPI = async (payload) => {
  let val = document.getElementById("row-page").value;
  let code = document.getElementById("code").value;
  let name = document.getElementById("name").value;
  let email = document.getElementById("email").value;
  let phone = document.getElementById("phone").value;
  let status = document.getElementById("status").value;

  if (payload == null || payload == "" || payload == undefined) {
      payload = { pageSize: val, pageIndex: 1 };
  }
  // if(code !== null && name != null && email != null && phone !== null && status != null){
  //     removePage();
  //     updatePage()
  //     return;
  // }
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

  let res = await axios.post(BASE_URL + '/searchByDto', payload)
      .catch(function (error) {
          if (error.response) {
              console.log(error.response.data.code);
              console.log(error.response.data.message);
              console.log(error.response.data.details);
          } else if (error.request) {
              console.log(error.request);
          } else {
              console.log('Error', error.message);
          }
      });
  // document.getElementById('end').innerText = data.totalElements;
  // document.getElementById('total').innerText = payload.pageIndex;
  console.log(res);
  return res;
}