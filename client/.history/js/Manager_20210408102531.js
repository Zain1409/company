const getCompany = async () => {
    try {
      payload = { pageNo: 0, pageSize: 5 };
      const response = await axios.post('http://localhost:8080/api/company/SearchCompanies',payload);
  
      const company = response.data;
      console.log(company);
    //   console.log(`GET: Here's the list of todos`, todoItems);
        var tmp = "";
            for(let i = 0; i < company.contance.length; i++){
                tmp +='<tr>';
                tmp    +=    '<td>'+company[i].code+'</td>';
                tmp        +=    '<td>'+company[i].name+'</td>';
                tmp        +=    '<td>'+company[i].email+'</td>';
                tmp        +=    '<td>'+company[i].phone_number+'</td>';
                tmp        +=    '<td id="'+company[i].status+'">'+company[i].status+'</td>';
                tmp        +=    '<td><button style="background: #30C0F0 0% 0% no-repeat padding-box;">Detail</button><button style="background: #D31212 0% 0% no-repeat padding-box;">Delete</button></td>';
                tmp        +='</tr>';
            }
            // document.getElementById("table_body").innerHTML = tmp;
                document.getElementById("table_body").innerHTML = tmp;
            return company;
    } catch (errors) {
      console.error(errors);
    }
  };
// getCompany();
document.getElementById("Search").onclick = function(){getCompany()};


// const getTodoItems = async () => {
//     try {
//       const response = await axios.get('http://localhost:8080/employee');
  
//       const todoItems = response.data;
  
//       console.log(`GET: Here's the list of todos`, todoItems);
  
//       return todoItems;
//     } catch (errors) {
//       console.error(errors);
//     }
//   };
//   getTodoItems();