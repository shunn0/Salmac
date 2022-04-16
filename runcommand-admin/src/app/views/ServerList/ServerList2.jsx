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

const ServerList2 = () => {
    const commandResponse = useSelector(
        (state) => state.runCommand.commandResponse
    )
    const [state, setState] = useState({})
    const [snackBarState, setSnackBarState] = React.useState({
        open: false,
        vertical: 'top',
        horizontal: 'center',
        snackbarMsg: '',
        type: 'success',
    })
    const { vertical, horizontal, open, snackbarMsg, type } = snackBarState
    const [executeCmdList, setExecuteCmdList] = useState([])
    function snackbarClose() {
        setSnackBarState({ ...snackBarState, open: false })
    }
    const snackbarOpen = (newState) => () => {
        setSnackBarState({ open: true, ...newState })
    }
    const handleChange = ({ target: { name, value } }) => {
        setState({
            ...state,
            [name]: value,
        })
    }
    const dispatch = useDispatch()
    useEffect(() => {
        if (!commandResponse) {
            return
        }
        if (
            commandResponse &&
            commandResponse.status === 'ok' &&
            commandResponse.data.length > 0
        ) {
            setSnackBarState({
                open: true,
                vertical: 'top',
                horizontal: 'right',
                snackbarMsg: 'Command Run Successfully!!!',
                type: 'success',
            })
            setExecuteCmdList(commandResponse.data)
        } else if (commandResponse && commandResponse.status === 'error') {
            setSnackBarState({
                open: true,
                vertical: 'top',
                horizontal: 'right',
                snackbarMsg: 'something wrong!!!',
                type: 'error',
            })
        }
    }, [commandResponse])
    const handleLoadPage = (event) => {
        try {
            dispatch(getServersList())
        } catch (e) {
            setSnackBarState({
                open: true,
                vertical: 'top',
                horizontal: 'right',
                snackbarMsg: 'Something wrong!!!',
                type: 'error',
            })
        }
    }
    const {
        multicmd,
    } = state
    return (
        <Container>
            <div className="breadcrumb">
                <Breadcrumb
                    routeSegments={[{ name: 'List of Server', path: '/' }]}
                />
            </div>
            <SimpleCard title="Servers List">
                <ValidatorForm onSubmit={handleSubmit} onError={() => null}>
                    <Grid item lg={12} md={12} sm={12} xs={12} sx={{ mt: 2 }}>
                        <Typography variant="h6" className="list-header">
                            Response
                        </Typography>
                        <hr />
                        <ul className="list-group">
                            {executeCmdList &&
                                executeCmdList.map((cmd, index) => (
                                    <ListItem divider key={index}>
                                        <p>{cmd}</p>
                                    </ListItem>
                                ))}
                        </ul>
                    </Grid>
                </ValidatorForm>
            </SimpleCard>
            <Box py="12px" />
            <Snackbar
                open={open}
                autoHideDuration={6000}
                onClose={snackbarClose}
                anchorOrigin={{ vertical, horizontal }}
            >
                <Alert
                    onClose={snackbarClose}
                    severity={type}
                    sx={{ width: '100%' }}
                >
                    {snackbarMsg}
                </Alert>
            </Snackbar>
        </Container>
    )
}

export default ServerList2
