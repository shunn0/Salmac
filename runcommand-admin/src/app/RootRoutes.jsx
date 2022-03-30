import React from 'react'
import { Redirect } from 'react-router-dom'
import runCmdRoutes from './views/runcmd/RunCmdRoutes'
import runCmdInputRoutes from './views/runcmd-input-file/RunCmdInputRoutes'

const redirectRoute = [
    {
        path: '/',
        exact: true,
        component: () => <Redirect to="/runcmd/default" />,
    },
]

const errorRoute = [
    {
        component: () => <Redirect to="/session/404" />,
    },
]

const routes = [
    ...runCmdRoutes,
    ...runCmdInputRoutes,
    ...redirectRoute,
    ...errorRoute,
]

export default routes
