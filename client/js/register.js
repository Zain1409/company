var addAPI = async (e) => {
    let code = document.getElementById("code").value;
    // let check = await checkCode('', code);

    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phoneNumber").value;
    if (document.getElementById('website') != null) {
        var website = document.getElementById("website").value;
    }

    var payload = { code: code, name: name, address: address, email: email, phoneNumber: phone, status: status }

   
        let res = await axios.post('http://localhost:8080/api/company', payload)
            .catch(function (error) {
                if (error.response) {
                   
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                   
                    console.log(error.request);
                } else {
    
                    console.log('Error', error.message);
                }

            });

        let data = res.data;

        if (data) {
            alert("Thêm thành công!");
        } else {
            alert("Có lỗi xảy ra!");
        }
        window.location.href = "http://127.0.0.1:5500/html/CompanyManager.html";
}

document.getElementById("register").addEventListener('change',async function(){
    await addAPI();
  })
document.getElementById("back").addEventListener("click", async function (e) {
   
    window.location.href = "http://127.0.0.1:5500/html/CompanyManager.html";
});
