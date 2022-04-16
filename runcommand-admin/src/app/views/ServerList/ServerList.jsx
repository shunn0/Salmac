import React, { useState, useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import {
    Button,
    Icon,
    Grid,
    TextareaAutosize,
    Snackbar,
    Alert,
    Typography,
    ListItem
} from '@mui/material'
import { Span } from 'app/components/Typography'
import { Breadcrumb, SimpleCard } from 'app/components'
import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator'
import { Box, styled } from '@mui/system'
import { getServersList } from 'app/redux/actions/RunCommandActions'

const Container = styled('div')(({ theme }) => ({
    margin: '30px',
    [theme.breakpoints.down('sm')]: {
        margin: '16px',
    },
    '& .breadcrumb': {
        marginBottom: '30px',
        [theme.breakpoints.down('sm')]: {
            marginBottom: '16px',
        },
    },
}))

const ServerList = () => {
    console.log("sdfdsfsd")
    return (
        <Container>
            <div className="breadcrumb">
                <Breadcrumb
                    routeSegments={[{ name: 'List of Server', path: '/' }]}
                />
            </div>
        </Container>
    )
}

export default ServerList
