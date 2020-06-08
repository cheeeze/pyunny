export default [{
    path: "/",
    view: "Home",
    name: "Home",
  },
  {
    path: "/test",
    view: "test",
    name: "test",
  },
  {
    path: "/recipecreate",
    view: "Recipecreate",
    name: "Recipecreate",
  },
  {
    path: "/recipe",
    view: "Recipe",
    name: "Recipe",
  },
  {
    path: "/recipedetail/:id",
    view: "Recipedetail",
    name: "Recipedetail",
    props: true,
  },
];