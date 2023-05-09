window.scrollTo({
  top: 0,
  behavior: "auto",
});

let cards = document.querySelectorAll(".card");
cards.forEach((item) => {
  item.addEventListener("mouseover", function () {
    item.classList.add("shadow", "bg-body", "rounded", "animationhover");
    $(this).find(".img").addClass("hovercardimg");
    $(this).find(".img").removeClass("unhovercardimg");
  });
  item.addEventListener("mouseout", function () {
    item.classList.remove("shadow", "bg-body", "rounded", "animationhover");
    $(this).find(".img").addClass("unhovercardimg");
    $(this).find(".img").removeClass("hovercardimg");
  });
});
