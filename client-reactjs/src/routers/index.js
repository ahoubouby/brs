import React from "react";
import login from "../component/login";
import notFound from "../pages/NotFound";

const DashboardComp = () => <div>dashboard component</div>;

const exportDashboard = {
  path: "/dashbaord",
  component: DashboardComp,
  name: "dashboard",
};

const routees = [login, exportDashboard, notFound];

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
