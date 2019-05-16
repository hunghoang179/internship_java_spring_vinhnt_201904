import React from 'react'

export default function Role(props) {

    function render(params) {
        if (params.role === 0) {
            return null;
        } else {
            return <div>{props.children}</div>;
        }
    }

    return (
        <div>{render(props.sessionUser)}</div>
    );
}

