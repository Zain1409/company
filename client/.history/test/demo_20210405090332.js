// setTimeout(function(){
//     console.log(1);
//     setTimeout(function(){
//       console.log(2);
//       setTimeout(function(){
//         console.log(3);
//         setTimeout(function(){
//           console.log(4);
//           setTimeout(function(){
//             console.log(5);
//           },1000);
//         },1000);
//       },1000);
//     },1000);
//   },1000);
  
// var promise = new Promise(
//     function (resolve, reject){
//         reject('Fail!');
//     }
// );

// promise
//     .then(function(resolve){
//         console.log(resolve);
//     })
//     .catch(
//         function(err){
//             console.log(err);
//         }
//     )
// var promise1 = new Promise(
//     function(resolve){
//         setTimeout(function(){
//             resolve([1]);
//         }, 2000);
//     }
// );
// var promise2 = new Promise(
//     function(resolve){
//         setTimeout(function(){
//             resolve([2,3]);
//         }, 5000);
//     }
// );
// Promise.all([promise1,promise2])
//     .then(function(result){
//         console.log(result);
//     })

// var postApi = 'https://jsonplaceholder.typicode.com/posts';

// fetch(postApi)
//     .then(function(response){
//         return response.json();
//     })
//     .then(function(post){
//         var tmp = '';
//        var htmls = post.map(function(post)
//        {
//             tmp += '<li>';
//             tmp += '<h2>'+post.title+'</h2>';
//             tmp += '<p>'+post.body+'<p>';
//             tmp += '</li>';
//             return tmp;
//        });
//        var html = htmls.join('');
//        document.getElementById('post-block').innerHTML = html;
//     }).catch(function(err){
//         console.log("co loi!");
//     });

// var postApi='https://jsonplaceholder.typicode.com/posts';
// fetch(postApi)
//   .then(response => response.json())
//   .then(data => console.log(data));

var courses = ' http://localhost:3000/courses';

var listCoursesBlock = 
    document.querySelector('#list-courses');
function start(){
    getCourses(re);
}
start();

//function
function getCourses(callback){
    fetch(courses)
    .then(Response => Response.json())
    .then(callback);
}
function renderCourse(){
    var listCoursesBlock = 
    document.querySelector('#list-courses');
    var tmp = '';
    var htmls = courses.map(function(course){
        tmp += '<li>';
        tmp += '<h4>'+course.name+'</h4>';
        tmp += '<p>'+course.discription+'</p>';
        tmp += '</li>';
        return tmp;
    });
    listCoursesBlock.innerHTML = htmls.join('');
}