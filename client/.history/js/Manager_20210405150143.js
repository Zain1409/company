const getCompany = async () => {
    try {
      const response = await axios.get('http://localhost:8080/employee');
  
      const company = response.data;
  
    //   console.log(`GET: Here's the list of todos`, todoItems);
        console.log(company);
        // var tmp = "";
        //     for(let i = 0; i < company.length; i++){
        //         tmp +='<tr>';
        //         tmp    +=    '<td>'+company[i].code+'</td>';
        //         tmp        +=    '<td>'+company[i].name+'</td>';
        //         tmp        +=    '<td>'+company[i].email+'</td>';
        //         tmp        +=    '<td>'+company[i].phone_number+'</td>';
        //         tmp        +=    '<td id="'+company[i].status+'">'+company[i].status+'</td>';
        //         tmp        +=    '<td><button style="background: #30C0F0 0% 0% no-repeat padding-box;">Detail</button><button style="background: #D31212 0% 0% no-repeat padding-box;">Delete</button></td>';
        //         tmp        +='</tr>';
        //     }
        //     document.getElementById("table_body").innerHTML = tmp;
            return company;
    } catch (errors) {
      console.error(errors);
    }
  };
  getCompany();