const bookInputEl = document.getElementById("editBook");
const editEl = document.getElementById("editModal");

editEl.addEventListener("shown.bs.modal", function() {
  bookInputEl.focus();
})