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


var getPage = async (id) => {

  var pageSize = document.getElementById("row-page").value;

  let payload = { pageSize: pageSize, pageIndex: id };

  let dataApi = await searchAPI(payload);
  let data = dataApi.data;

  console.log(data);
  if (Number(pageSize) > Number(data.content.length)) {
      document.getElementById('end').innerText = data.content.length + (data.number * data.size);
  } else {
      document.getElementById('end').innerText = (data.number + 1) * data.size;
  }
  document.getElementById('start').innerText = data.number * data.size + 1;
  document.getElementById('total').innerText = id;
  await removeData();
  await updateCompanyList(data);
  await removePage();
  await updatePage(data.totalPages);

  localStorage.setItem("pageCurrent", id);
}