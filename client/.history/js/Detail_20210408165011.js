// var URL = "http://localhost:8080/api/company";
// const getCompanyById = async (id) => {
//     let res = await axios.get(URL + '/' + id)
//         .catch(function (error) {
//             if (error.response) {
//                 console.log(error.response.data.code);
//                 console.log(error.response.data.message);
//                 console.log(error.response.data.details);
//             } else if (error.request) {
//                 console.log(error.request);
//             } else {
//                 console.log('Error', error.message);
//             }
//         });
//     return res.data;
// }
// var main = async () => {
//     let id = localStorage.getItem('id');
//     let data = await getCompanyById(id);
//     console.log(data);

//     document.getElementById('code').value = data.code;
//     document.getElementById('name').value = data.name;
//     document.getElementById('address').value = data.address;
//     document.getElementById('email').value = data.email;
//     document.getElementById('phone_number').value = data.phoneNumber;
//     document.getElementById('website').value = data.website;
//     document.getElementById('form_select').value = data.status;

// }
// main();
// var update = async () => {
//     let code = document.getElementById("code").value;
//     let id = localStorage.getItem('id');

//     let name = document.getElementById("name").value;
//     let address = document.getElementById("address").value;
//     let email = document.getElementById("email").value;
//     let phone = document.getElementById("phone_number").value;
//     if (document.getElementById('website') != null) {
//         var website = document.getElementById("website").value;
//     }
//     let status = document.getElementById('form_select').value;

//     var payload = { code: code, name: name, address: address, email: email, phoneNumber: phone, status: status }

//     if (website !== "" || website !== undefined || website !== null) {
//         Object.assign(payload, { website: website });
//     }
//     // if (check) {
//     //     alert("Trùng!");
//     //     return;
//     // } else {
//         let res = await axios.put('http://localhost:8080/api/company/' + id, payload)
//             .catch(function (error) {
//                 if (error.response) {
//                     console.log(error.response.data.code);
//                     console.log(error.response.data.message);
//                     console.log(error.response.data.details);
//                 } else if (error.request) {

//                     console.log(error.request);
//                 } else {

//                     console.log('Error', error.message);
//                 }

//             });

//         let data = res.data;

//         if (data) {
//             alert("Sửa thành công!");
//         } else {
//             alert("Có lỗi xảy ra!");
//         }
//         window.location.href = "http://127.0.0.1:5500/html/CompanyManager.html";
//     // }
// }
// document.getElementById("back").addEventListener("click", async function (e) {
   
//     window.location.href = "http://127.0.0.1:5500/html/CompanyManager.html";
// });
// document.getElementById("update").onclick = function(){update()};


const BASE_URL = 'http://localhost:8080/api/company';




$(function () {
    $("#confirm").on("click", function (e) {
        var form = $("#cert-form")[0];
        var isValid = form.checkValidity();
        if (!isValid) {
            e.preventDefault();
            e.stopPropagation();
        } else {
            update();
        }
        form.classList.add('was-validated');
        //window.location.href = "http://127.0.0.1:5500//layout/management.html";
        return false; // For testing only to stay on this page
    });
});

document.getElementById("back").addEventListener("click", async function (e) {
    e.preventDefault();
    e.stopPropagation();
    window.location.href = "http://127.0.0.1:5500//layout/management.html";
});

const getCompanyById = async (id) => {
    let res = await axios.get(BASE_URL + '/' + id)
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
    return res.data;
}

var main = async () => {
    let id = localStorage.getItem('id');
    let data = await getCompanyById(id);
    console.log(data);

    document.getElementById('code').value = data.code;
    document.getElementById('name').value = data.name;
    document.getElementById('address').value = data.address;
    document.getElementById('email').value = data.email;
    document.getElementById('phone').value = data.phoneNumber;
    document.getElementById('website').value = data.website;
    document.getElementById('status').value = data.status;

}

main();

const checkCode = async (id, code) => {
    let res = await axios.get('http://localhost:6262/api/company/checkCode?id=' + id + '&code=' + code);
    let result = res.data;
    return result;
}
var update = async () => {
    let code = document.getElementById("code").value;
    let id = localStorage.getItem('id');
    let check = await checkCode(id, code);

    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    if (document.getElementById('website') != null) {
        var website = document.getElementById("website").value;
    }
    let status = document.getElementById('status').value;

    var payload = { code: code, name: name, address: address, email: email, phoneNumber: phone, status: status }

    if (website !== "" || website !== undefined || website !== null) {
        Object.assign(payload, { website: website });
    }
    if (check) {
        alert("Trùng!");
        return;
    } else {
        let res = await axios.put('http://localhost:6262/api/company/' + id, payload)
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

        let data = res.data;

        if (data) {
            alert("Sửa thành công!");
        } else {
            alert("Có lỗi xảy ra!");
        }
        window.location.href = "http://127.0.0.1:5500//layout/management.html";
    }
}