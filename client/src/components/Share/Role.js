import React from 'react'

export default function Role(props) {

    function rolePublic(params) {
        if (params.role === 0) {
            return null;
        } else {
            return <span>{props.children}</span>;
        }
    }

    function rolePrivate(params) {
        if (params.role !== 2) {
            return null;
        } else {
            return <span>{props.children}</span>;
        }
    }

    function roleAdmin(props) {
        if (props.sessionUser.role === 1 && props.role === 0) {
            return <span>{props.children}</span>;
        } else if (props.sessionUser.role === 2) {
            return <span>{props.children}</span>;
        } else {
            return null;
        }
    }

    return (
        <span>
            {(props.status === 1) ? rolePublic(props.sessionUser) : ''}
            {(props.status === 2) ? rolePrivate(props.sessionUser) : ''}
            {(props.status === 3) ? roleAdmin(props) : ''}
        </span>
    );
}

