import React from "react";
import login from "../component/login";
import notFound from "../pages/NotFound";

const DashboardComp = () => <div>dashboard component</div>;
const HomeCom = () => <div> hello home</div>;

const exportDashboard = {
  path: "/dashbaord",
  component: DashboardComp,
  name: "dashboard",
};

const exportHomeCom = {
  path: "/home",
  component: HomeCom,
  name: "Home",
};
const routees = [login, exportDashboard, exportHomeCom, notFound];

export { routees };

//********************************************************/
/*
 routeProps: {
    path: '/todo',
    component: memo(TodoApp),
    },
    name: 'todo App simple',
}
*/

//********************************************************/
