var favouriteMoviesListDB=[];
var moviesListDB=[];
function getMovies() {
	return	fetch("http://localhost:3000/movies").
			then(response=>response.json())
			.then(moviesList=>{
				moviesListDB=moviesList;
			let textHtml='';
			const moviesPanel=document.getElementById('moviesList');
			moviesList.forEach(movie => {
				textHtml=textHtml+`
				<li class="list-group-item">${movie.title}</li>
				<li class="list-group-item">
				<img src="${movie.posterPath}" width="100" heigth="100">
				</img></li> 
				<li class="list-group-item">${movie.description}</li>
				<button type="button" onClick=addFavourite(${movie.id}) 
				class="btn btn-secondary">Add Favourites</button>
				`;
				moviesPanel.innerHTML=textHtml;
				
			});
			return moviesList.json();
		
	}).catch(err=>{
		return Promise.reject(null);
	});
}

function getFavourites() {
	return	fetch("http://localhost:3000/favourites").
		then(response=>response.json())
		.then(favMoviesList=>{
			favouriteMoviesListDB=favMoviesList;
			let textHtml='';
			const moviesPanel=document.getElementById('favouritesList');
			favMoviesList.forEach(favMovie => {
				textHtml=textHtml+`<li class="list-group-item">${favMovie.title}</li>
				<li class="list-group-item"><img src="${favMovie.posterPath}" width="100" heigth="100">
				</img></li> <li class="list-group-item">${favMovie.description}</li>
				<button type="button" onClick=removeFavourite(${favMovie.id}) 
				class="btn btn-secondary">Remove Favourites</button>
				`;
				moviesPanel.innerHTML=textHtml;
			});
			return favMoviesList.json();
		}).catch(err=>{
			return Promise.reject(null);
		})
}

function addFavourite(movieId) {
		let selectedMovie = moviesListDB.find(movie =>movie.id ===movieId); 
		// Check if the selected movie is already added to favourites
		let movieExistsInFav = favouriteMoviesListDB.find(movie => movie.id === movieId);
	
		// Return if the selected movie is already part of favourites
		if (movieExistsInFav) {
			return Promise.reject(new Error('Movie is already added to favourites'));
		}
	//Fetch POST
	return fetch('http://localhost:3000/favourites', {
		method: 'POST',
		headers: {
			'content-type': 'application/json'
		},
		body: JSON.stringify(selectedMovie)
	}).then(response =>response.json())
	.then(addedMovie => {
		favouriteMoviesListDB.push(addedMovie);
		
		return favouriteMoviesListDB;
	}).catch(error => {
		return error;
	})
			
}

module.exports = {
	getMovies,
	getFavourites,
	addFavourite
};

// You will get error - Uncaught ReferenceError: module is not defined
// while running this script on browser which you shall ignore
// as this is required for testing purposes and shall not hinder
// it's normal execution


